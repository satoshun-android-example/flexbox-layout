package com.github.satoshun.compose.ui.flexbox.preview

import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.github.satoshun.compose.ui.flexbox.Flexbox
import com.github.satoshun.example.JustifyContent

@Preview
@Composable
internal fun JustifyContentStartPreview() {
  Flexbox(justifyContent = JustifyContent.FlexStart) {
    FlexboxSample()
  }
}

@Preview
@Composable
internal fun JustifyContentEndPreview() {
  Flexbox(justifyContent = JustifyContent.FlexEnd) {
    FlexboxSample()
  }
}
