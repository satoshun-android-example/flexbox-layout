package com.github.satoshun.example

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

@Composable
fun SampleApp() {
  Surface(color = MaterialTheme.colors.background) {
    Scaffold(
      drawerContent = {
        AppDrawer {
        }
      }
    ) {
      Flexbox {
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
    }
  }
}

@Composable
fun AppDrawer(closeDrawer: () -> Unit) {
  Column(modifier = Modifier.fillMaxSize()) {
    Row {
      Button(onClick = {}) {
      }
    }
  }
}

@Preview("sample app")
@Composable
fun PreviewSampleApp() {
  SampleApp()
}
