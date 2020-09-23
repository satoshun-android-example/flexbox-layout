package com.github.satoshun.example

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.state
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.core.graphics.component1
import androidx.core.graphics.component2
import androidx.ui.tooling.preview.Preview

@Composable
fun SampleTheme(
  content: @Composable () -> Unit
) {
  MaterialTheme(
    content = content
  )
}

@Composable
fun SampleApp(
  scaffoldState: ScaffoldState = rememberScaffoldState()
) {
  val (direction, setDirection) = remember {
    mutableStateOf<FlexDirection>(FlexDirection.Row)
  }

  Surface(color = MaterialTheme.colors.background) {
    Scaffold(
      scaffoldState = scaffoldState,
      topBar = {
        TopAppBar(
          title = { Text(text = "sample app") },
          navigationIcon = {
            IconButton(onClick = { scaffoldState.drawerState.open() }) {
            }
          }
        )
      },
      drawerContent = {
        AppDrawer(
          changeDirection = {
            setDirection(it)
            scaffoldState.drawerState.close()
          },
          closeDrawer = {
            scaffoldState.drawerState.close()
          }
        )
      }
    ) {
      Flexbox(
        direction = direction,
      ) {
        Text("test1", style = MaterialTheme.typography.h3)
        Text("test2")
        Spacer(Modifier.preferredWidth(24.dp))
        Text("test3")

        Row(Modifier.fillMaxWidth()) {
          Text("row1")
          Text("row2")
        }

        Text("test1", style = MaterialTheme.typography.h2)

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
fun AppDrawer(
  changeDirection: (FlexDirection) -> Unit,
  closeDrawer: () -> Unit
) {
  Column(modifier = Modifier.fillMaxSize()) {
    Spacer(modifier = Modifier.preferredHeight(8.dp))
    Text("Direction", style = MaterialTheme.typography.h3)
    Spacer(modifier = Modifier.preferredHeight(8.dp))
    Row {
      Button(onClick = { changeDirection(FlexDirection.Row) }) {
        Text("Row")
      }
      Spacer(modifier = Modifier.preferredWidth(8.dp))

      Button(onClick = { changeDirection(FlexDirection.RowReverse) }) {
        Text("RowReverse")
      }
      Spacer(modifier = Modifier.preferredWidth(8.dp))

      Button(onClick = { changeDirection(FlexDirection.Column) }) {
        Text("Column")
      }
      Spacer(modifier = Modifier.preferredWidth(8.dp))

      Button(onClick = { changeDirection(FlexDirection.ColumnReverse) }) {
        Text("ColumnReverse")
      }
    }
  }
}

@Preview("sample app")
@Composable
fun PreviewSampleApp() {
  SampleApp()
}
