package com.github.satoshun.compose.ui.flexbox

import androidx.compose.runtime.Composable
import androidx.compose.ui.Layout
import androidx.compose.ui.Modifier
import com.github.satoshun.example.AlignContent
import com.github.satoshun.example.AlignItems
import com.github.satoshun.example.AlignSelf
import com.github.satoshun.example.FlexDirection
import com.github.satoshun.example.FlexWrap
import com.github.satoshun.example.JustifyContent
import kotlin.math.max

// TODO support FlexboxScope
// TDOO support maxLine
@Composable
fun Flexbox(
  modifier: Modifier = Modifier,
  wrap: FlexWrap = FlexWrap.Wrap,
  direction: FlexDirection = FlexDirection.Row,
  justifyContent: JustifyContent = JustifyContent.FlexStart,
  alignContent: AlignContent = AlignContent.FlexStart,
  alignItems: AlignItems = AlignItems.FlexStart,
  alignSelf: AlignSelf = AlignSelf.Auto,
  children: @Composable () -> Unit
) {
  if (direction == FlexDirection.Row) {
    Layout(modifier = modifier, children = children) { measurables, constraints ->
      val placeables = measurables.map {
        val placeable = it.measure(constraints)
        placeable
      }
      val layoutWidth = constraints.maxWidth
      val layoutHeight = constraints.maxHeight

      layout(layoutWidth, layoutHeight) {
        var rowX = 0
        var currentY = 0
        var nextMaxHeight = 0
        var line = 0

        val flexLines = mutableListOf<FlexLine>()

        var flexLine = FlexLine(line = 0)
        placeables.forEachIndexed { index, placeable ->
          // TODO check if first capacity over item
          if (rowX + placeable.width > layoutWidth) {
            flexLines += flexLine
            rowX = 0
            currentY += nextMaxHeight
            nextMaxHeight = 0
            line += 1
            flexLine = FlexLine(line = line)
          }
          flexLine.items.add(
            FlexItemPosition(
              index = index,
              x = rowX,
              y = currentY,
              width = placeable.width,
              height = placeable.height
            )
          )
          rowX += placeable.width
          nextMaxHeight = max(nextMaxHeight, placeable.height)
        }
        flexLines += flexLine

        when (justifyContent) {
          JustifyContent.FlexStart -> {
            // do nothing
          }
          JustifyContent.FlexEnd -> {
            flexLines.forEach { it.toFlexEnd(layoutWidth) }
          }
          JustifyContent.Center -> {
            flexLines.forEach { it.toCenter(layoutWidth) }
          }
        }

        when (wrap) {
          FlexWrap.Wrap -> {
            placeables.forEachIndexed { index, placeable ->
              val target = flexLines.findByIndex(index)
              placeable.placeRelative(
                x = target.x,
                y = target.y
              )
            }
          }
          FlexWrap.WrapReverse -> {
            var y = 0
            var next: Int
            while (line >= 0) {
              next = 0

              val fl = flexLines[line]
              fl.items.map { position ->
                val placeable = placeables[position.index]
                placeable.placeRelative(
                  x = position.x,
                  y = y
                )
                next = max(next, position.height)
              }

              y += next
              line -= 1
            }
          }
        }
      }
    }
  } else if (direction == FlexDirection.RowReverse) {
    Layout(modifier = modifier, children = children) { measurables, constraints ->
      val placeables = measurables.map {
        val placeable = it.measure(constraints)
        placeable
      }
      val layoutWidth = constraints.maxWidth
      val layoutHeight = constraints.maxHeight

      layout(layoutWidth, layoutHeight) {
        var rowX = layoutWidth
        var currentY = 0
        var nextMaxHeight = 0

        placeables.forEach { placeable ->
          if (rowX - placeable.width < 0) {
            rowX = layoutWidth
            currentY += nextMaxHeight
            nextMaxHeight = 0
          }
          rowX -= placeable.width
          placeable.placeRelative(
            x = rowX,
            y = currentY
          )
          nextMaxHeight = max(nextMaxHeight, placeable.height)
        }
      }
    }
  } else if (direction == FlexDirection.Column) {
    Layout(modifier = modifier, children = children) { measurables, constraints ->
      val placeables = measurables.map {
        val placeable = it.measure(constraints)
        placeable
      }
      val layoutWidth = constraints.maxWidth
      val layoutHeight = constraints.maxHeight

      layout(layoutWidth, layoutHeight) {
        var rowY = 0
        var currentX = 0
        var nextMaxWidth = 0

        placeables.forEach { placeable ->
          if (rowY + placeable.height > layoutHeight) {
            rowY = 0
            currentX += nextMaxWidth
            nextMaxWidth = 0
          }
          placeable.placeRelative(
            x = currentX,
            y = rowY
          )
          rowY += placeable.height
          nextMaxWidth = max(nextMaxWidth, placeable.width)
        }
      }
    }
  } else if (direction == FlexDirection.ColumnReverse) {
    Layout(modifier = modifier, children = children) { measurables, constraints ->
      val placeables = measurables.map {
        val placeable = it.measure(constraints)
        placeable
      }
      val layoutWidth = constraints.maxWidth
      val layoutHeight = constraints.maxHeight

      layout(layoutWidth, layoutHeight) {
        var rowY = layoutHeight
        var currentX = 0
        var nextMaxWidth = 0

        placeables.forEach { placeable ->
          if (rowY - placeable.height < 0) {
            rowY = layoutHeight
            currentX += nextMaxWidth
            nextMaxWidth = 0
          }
          rowY -= placeable.height
          placeable.placeRelative(
            x = currentX,
            y = rowY
          )
          nextMaxWidth = max(nextMaxWidth, placeable.width)
        }
      }
    }
  }
}

internal data class FlexLine(
  var items: MutableList<FlexItemPosition> = mutableListOf(),
  val line: Int
)

// use from Row direction
internal fun FlexLine.toFlexEnd(maxWidth: Int) {
  var x = maxWidth

  items = items.asReversed()
    .map {
      x -= it.width
      it.copy(x = x)
    }
    .toMutableList()
}

// use from Row direction
internal fun FlexLine.toCenter(maxWidth: Int) {
  var x = (maxWidth - lineWidth) / 2

  items = items
    .map {
      val newItem = it.copy(x = x)
      x += it.width
      newItem
    }
    .toMutableList()
}

internal val FlexLine.lineWidth: Int
  get() = items.sumBy { it.width }

internal data class FlexItemPosition(
  val index: Int = 0,
  val x: Int = 0,
  val y: Int = 0,
  val width: Int = 0,
  val height: Int = 0
)

internal fun List<FlexLine>.findByIndex(target: Int): FlexItemPosition {
  forEach { line ->
    val findOrNull = line.items.firstOrNull { it.index == target }
    if (findOrNull != null) return findOrNull
  }
  throw IllegalArgumentException("illegal target index $target")
}
