package com.github.satoshun.example

sealed class FlexWrap {
  object Wrap : FlexWrap()
}

sealed class FlexDirection {
  object Row : FlexDirection()
  object RowReverse : FlexDirection()
  object Column : FlexDirection()
  object ColumnReverse : FlexDirection()
}

sealed class JustifyContent {
  object FlexStart : JustifyContent()
}

sealed class AlignContent {
  object FlexStart : AlignContent()
}

sealed class AlignItems {
  object FlexStart : AlignItems()
}

sealed class AlignSelf {
  object Auto : AlignSelf()
}
