package com.github.satoshun.example

import androidx.compose.runtime.Composable
import androidx.compose.ui.Layout
import androidx.compose.ui.Modifier
import kotlin.math.max

sealed class FlexWrap {
  object Wrap : FlexWrap()
}

sealed class JustifyContent {
  object FlexStart : JustifyContent()
}

sealed class FlexDirection {
  object Row : FlexDirection()
}

@Composable
fun Flexbox(
  modifier: Modifier = Modifier,
  wrap: FlexWrap = FlexWrap.Wrap,
  direction: FlexDirection = FlexDirection.Row,
  justifyContent: JustifyContent = JustifyContent.FlexStart,
  children: @Composable () -> Unit
) {
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
}
