package com.github.satoshun.example

sealed class FlexWrap {
  object Wrap : FlexWrap()
  object WrapReverse : FlexWrap()
}

sealed class FlexDirection {
  object Row : FlexDirection()
  object RowReverse : FlexDirection()
  object Column : FlexDirection()
  object ColumnReverse : FlexDirection()
}

sealed class JustifyContent {
  object FlexStart : JustifyContent()
  object FlexEnd : JustifyContent()
  object Center : JustifyContent()
  object SpaceBetween : JustifyContent()
  object SpaceAround : JustifyContent()
  object SpaceEvenly : JustifyContent()
}

sealed class AlignContent {
  object FlexStart : AlignContent()
  object FlexEnd : AlignContent()
  object Center : AlignContent()
  object Stretch : AlignContent()
  object SpaceBetween : AlignContent()
  object SpaceAround : AlignContent()
}

sealed class AlignItems {
  object FlexStart : AlignItems()
  object FlexEnd : AlignItems()
  object Center : AlignItems()
  object Stretch : AlignItems()
  object Baseline : AlignItems()
}

sealed class AlignSelf {
  object Auto : AlignSelf()
}
