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

        val flexLines = mutableListOf<FlexRowLine>()

        var flexLine = FlexRowLine(line = 0, y = 0)
        placeables.forEachIndexed { index, placeable ->
          // TODO check if first capacity over item
          if (rowX + placeable.width > layoutWidth) {
            flexLines += flexLine
            rowX = 0
            currentY += nextMaxHeight
            nextMaxHeight = 0
            line += 1
            flexLine = FlexRowLine(line = line, y = currentY)
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
          JustifyContent.SpaceBetween -> {
            flexLines.forEach { it.toSpaceBetween(layoutWidth) }
          }
          JustifyContent.SpaceAround -> {
            flexLines.forEach { it.toSpaceAround(layoutWidth) }
          }
          JustifyContent.SpaceEvenly -> {
            flexLines.forEach { it.toSpaceEvenly(layoutWidth) }
          }
        }

        when (alignContent) {
          AlignContent.FlexStart -> {
            // do nothing
          }
          AlignContent.FlexEnd -> {
            val totalHeight = flexLines.totalHeight
            flexLines.forEach {
              it.toAlignContentFlexEnd(
                maxHeight = layoutHeight,
                totalHeight = totalHeight
              )
            }
          }
          AlignContent.Center -> {
            val totalHeight = flexLines.totalHeight
            flexLines.forEach {
              it.toAlignContentCenter(
                maxHeight = layoutHeight,
                totalHeight = totalHeight
              )
            }
          }
          AlignContent.Stretch -> {
            val totalHeight = flexLines.totalHeight
            val lineSize = flexLines.size
            flexLines.forEach {
              it.toAlignContentStretch(
                maxHeight = layoutHeight,
                totalHeight = totalHeight,
                lineSize = lineSize
              )
            }
          }
          AlignContent.SpaceBetween -> {
            val totalHeight = flexLines.totalHeight
            val lineSize = flexLines.size
            flexLines.forEach {
              it.toAlignContentSpaceBetween(
                maxHeight = layoutHeight,
                totalHeight = totalHeight,
                lineSize = lineSize
              )
            }
          }
          AlignContent.SpaceAround -> {
            val totalHeight = flexLines.totalHeight
            val lineSize = flexLines.size
            flexLines.forEach {
              it.toAlignContentSpaceAround(
                maxHeight = layoutHeight,
                totalHeight = totalHeight,
                lineSize = lineSize
              )
            }
          }
        }

        when (alignItems) {
          AlignItems.FlexStart -> {
            // do nothing
          }
          AlignItems.FlexEnd -> {
            flexLines.forEach {
              it.toAlignItemsFlexEnd()
            }
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

internal data class FlexRowLine(
  var items: MutableList<FlexItemPosition> = mutableListOf(),
  val line: Int,
  var y: Int
)

// use from Row direction
internal fun FlexRowLine.toFlexEnd(maxWidth: Int) {
  var x = maxWidth

  items = items.asReversed()
    .map {
      x -= it.width
      it.copy(x = x)
    }
    .toMutableList()
}

// use from Row direction
internal fun FlexRowLine.toCenter(maxWidth: Int) {
  var x = (maxWidth - lineWidth) / 2

  items = items
    .map {
      val newItem = it.copy(x = x)
      x += it.width
      newItem
    }
    .toMutableList()
}

// use from Row direction
internal fun FlexRowLine.toSpaceBetween(maxWidth: Int) {
  var x = 0

  val remainWidth = maxWidth - lineWidth
  val space = if (itemSize <= 1) 0 else remainWidth / (itemSize - 1)

  items = items
    .map {
      val newItem = it.copy(x = x)
      x += it.width + space
      newItem
    }
    .toMutableList()
}

// use from Row direction
internal fun FlexRowLine.toSpaceAround(maxWidth: Int) {
  val remainWidth = maxWidth - lineWidth
  val space = if (itemSize == 0) {
    0
  } else {
    remainWidth / (itemSize * 2)
  }

  var x = space

  items = items
    .map {
      val newItem = it.copy(x = x)
      x += it.width + (space * 2)
      newItem
    }
    .toMutableList()
}

internal fun FlexRowLine.toSpaceEvenly(maxWidth: Int) {
  val remainWidth = maxWidth - lineWidth
  val space = if (itemSize == 0) {
    0
  } else {
    remainWidth / (itemSize + 1)
  }

  var x = space

  items = items
    .map {
      val newItem = it.copy(x = x)
      x += it.width + space
      newItem
    }
    .toMutableList()
}

internal fun FlexRowLine.toAlignContentFlexEnd(maxHeight: Int, totalHeight: Int) {
  val startY = maxHeight - totalHeight
  val newY = y + startY
  y = newY
  items = items
    .map { it.copy(y = newY) }
    .toMutableList()
}

internal fun FlexRowLine.toAlignContentCenter(maxHeight: Int, totalHeight: Int) {
  val startY = (maxHeight - totalHeight) / 2
  val newY = y + startY
  y = newY
  items = items
    .map { it.copy(y = newY) }
    .toMutableList()
}

internal fun FlexRowLine.toAlignContentStretch(
  maxHeight: Int,
  totalHeight: Int,
  lineSize: Int
) {
  val space = (maxHeight - totalHeight) / lineSize
  val newY = y + (space * line)
  y = newY
  items = items
    .map { it.copy(y = newY) }
    .toMutableList()
}

internal fun FlexRowLine.toAlignContentSpaceBetween(
  maxHeight: Int,
  totalHeight: Int,
  lineSize: Int
) {
  if (lineSize <= 1) return

  val space = (maxHeight - totalHeight) / (lineSize - 1)
  val newY = y + (space * line)
  y = newY
  items = items
    .map { it.copy(y = newY) }
    .toMutableList()
}

internal fun FlexRowLine.toAlignContentSpaceAround(
  maxHeight: Int,
  totalHeight: Int,
  lineSize: Int
) {
  val space = (maxHeight - totalHeight) / (lineSize + 1)
  val newY = y + (space * (line + 1))
  y = newY
  items = items
    .map { it.copy(y = newY) }
    .toMutableList()
}

internal fun FlexRowLine.toAlignItemsFlexEnd() {
  val maxHeight = maxHeight
  items = items
    .map { it.copy(y = y + maxHeight - it.height) }
    .toMutableList()
}

internal val FlexRowLine.lineWidth: Int
  get() = items.sumBy { it.width }

internal val FlexRowLine.itemSize: Int
  get() = items.size

internal val FlexRowLine.maxHeight: Int
  get() = items.maxOf { it.height }

internal val List<FlexRowLine>.totalHeight: Int
  get() = last().y + last().items.maxOf { it.height }

internal data class FlexItemPosition(
  val index: Int = 0,
  val x: Int = 0,
  val y: Int = 0,
  val width: Int = 0,
  val height: Int = 0
)

internal fun List<FlexRowLine>.findByIndex(target: Int): FlexItemPosition {
  forEach { line ->
    val findOrNull = line.items.firstOrNull { it.index == target }
    if (findOrNull != null) return findOrNull
  }
  throw IllegalArgumentException("illegal target index $target")
}
