package com.github.satoshun.compose.ui.flexbox.preview

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun FlexboxSample() {
  Text("test1")
  Text("test2")
  Spacer(Modifier.preferredWidth(24.dp))
  Text("test3")

  Row(Modifier.fillMaxWidth()) {
    Text("row1")
    Text("row2")
  }

  Text("test1")

  Column {
    Text("column1")
    Text("column2")
  }

  Text("test1")
}
