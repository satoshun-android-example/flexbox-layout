package com.github.satoshun.example

import androidx.compose.runtime.Composable
import androidx.compose.ui.Layout
import androidx.compose.ui.Modifier
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

        placeables.forEach { placeable ->
          if (rowX + placeable.width > layoutWidth) {
            rowX = 0
            currentY += nextMaxHeight
            nextMaxHeight = 0
          }
          placeable.placeRelative(
            x = rowX,
            y = currentY
          )
          rowX += placeable.width
          nextMaxHeight = max(nextMaxHeight, placeable.height)
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
