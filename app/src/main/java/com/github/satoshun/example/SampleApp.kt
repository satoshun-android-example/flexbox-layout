package com.github.satoshun.example

import androidx.compose.foundation.Box
import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.github.satoshun.compose.ui.flexbox.Flexbox

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
  val (wrap, setWrap) = remember {
    mutableStateOf<FlexWrap>(FlexWrap.Wrap)
  }
  val (justifyContent, setJustifyContent) = remember {
    mutableStateOf<JustifyContent>(JustifyContent.FlexStart)
  }
  val (alignContent, setAlignContent) = remember {
    mutableStateOf<AlignContent>(AlignContent.FlexStart)
  }
  val (alignItems, setAlignItems) = remember {
    mutableStateOf<AlignItems>(AlignItems.FlexStart)
  }

  Surface(color = MaterialTheme.colors.background) {
    Scaffold(
      scaffoldState = scaffoldState,
      topBar = {
        TopAppBar(
          title = { Text(text = "sample app") },
          navigationIcon = {
            IconButton(onClick = { scaffoldState.drawerState.open() }) {
              Icon(Icons.Filled.Home)
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
          changeWrap = {
            setWrap(it)
            scaffoldState.drawerState.close()
          },
          changeJustifyContent = {
            setJustifyContent(it)
            scaffoldState.drawerState.close()
          },
          changeAlignContent = {
            setAlignContent(it)
            scaffoldState.drawerState.close()
          },
          changeAlignItems = {
            setAlignItems(it)
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
        wrap = wrap,
        justifyContent = justifyContent,
        alignContent = alignContent,
        alignItems = alignItems,
      ) {
        Box(modifier = Modifier.size(80.dp), backgroundColor = Color.Blue)
        Box(modifier = Modifier.size(120.dp), backgroundColor = Color.Red)
        Box(modifier = Modifier.size(24.dp), backgroundColor = Color.Cyan)
        Box(modifier = Modifier.size(80.dp), backgroundColor = Color.Green)

        Row(Modifier.fillMaxWidth()) {
          Box(
            modifier = Modifier.size(40.dp).align(Alignment.CenterVertically),
            backgroundColor = Color.Green
          )
          Box(modifier = Modifier.size(160.dp), backgroundColor = Color.Magenta)
        }

        Box(modifier = Modifier.size(24.dp), backgroundColor = Color.Yellow)

        Column {
          Box(modifier = Modifier.size(60.dp), backgroundColor = Color.DarkGray)
          Box(modifier = Modifier.size(90.dp), backgroundColor = Color.Black)
        }

        Box(modifier = Modifier.size(200.dp), backgroundColor = Color.Blue)
      }
    }
  }
}

@Composable
fun AppDrawer(
  changeDirection: (FlexDirection) -> Unit,
  changeWrap: (FlexWrap) -> Unit,
  changeJustifyContent: (JustifyContent) -> Unit,
  changeAlignContent: (AlignContent) -> Unit,
  changeAlignItems: (AlignItems) -> Unit,
  closeDrawer: () -> Unit
) {
  ScrollableColumn(modifier = Modifier.fillMaxSize()) {
    Spacer(modifier = Modifier.preferredHeight(8.dp))
    Text("Direction", style = MaterialTheme.typography.h4)
    Spacer(modifier = Modifier.preferredHeight(8.dp))
    Column {
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
      }

      Spacer(modifier = Modifier.preferredHeight(8.dp))

      Row {
        Button(onClick = { changeDirection(FlexDirection.ColumnReverse) }) {
          Text("ColumnReverse")
        }
      }
    }

    Spacer(modifier = Modifier.preferredHeight(12.dp))

    Text("Wrap", style = MaterialTheme.typography.h4)
    Spacer(modifier = Modifier.preferredHeight(8.dp))
    Row {
      Button(onClick = { changeWrap(FlexWrap.Wrap) }) {
        Text("Wrap")
      }
      Spacer(modifier = Modifier.preferredWidth(8.dp))

      Button(onClick = { changeWrap(FlexWrap.WrapReverse) }) {
        Text("WrapReverse")
      }
    }

    Spacer(modifier = Modifier.preferredHeight(12.dp))

    Text("JustifyContent", style = MaterialTheme.typography.h4)
    Spacer(modifier = Modifier.preferredHeight(8.dp))
    Column {
      Row {
        Button(onClick = { changeJustifyContent(JustifyContent.FlexStart) }) {
          Text("FlexStart")
        }
        Spacer(modifier = Modifier.preferredWidth(8.dp))

        Button(onClick = { changeJustifyContent(JustifyContent.FlexEnd) }) {
          Text("FlexEnd")
        }
        Spacer(modifier = Modifier.preferredWidth(8.dp))

        Button(onClick = { changeJustifyContent(JustifyContent.Center) }) {
          Text("Center")
        }
        Spacer(modifier = Modifier.preferredWidth(8.dp))
      }

      Spacer(modifier = Modifier.preferredHeight(8.dp))
      Row {
        Button(onClick = { changeJustifyContent(JustifyContent.SpaceBetween) }) {
          Text("SpaceBetween")
        }
      }

      Spacer(modifier = Modifier.preferredHeight(8.dp))
      Row {
        Button(onClick = { changeJustifyContent(JustifyContent.SpaceAround) }) {
          Text("SpaceAround")
        }
      }

      Spacer(modifier = Modifier.preferredHeight(8.dp))
      Row {
        Button(onClick = { changeJustifyContent(JustifyContent.SpaceEvenly) }) {
          Text("SpaceEvenly")
        }
      }
    }

    Text("AlignContent", style = MaterialTheme.typography.h4)
    Spacer(modifier = Modifier.preferredHeight(8.dp))
    Column {
      Row {
        Button(onClick = { changeAlignContent(AlignContent.FlexStart) }) {
          Text("FlexStart")
        }
        Spacer(modifier = Modifier.preferredWidth(8.dp))

        Button(onClick = { changeAlignContent(AlignContent.FlexEnd) }) {
          Text("FlexEnd")
        }
        Spacer(modifier = Modifier.preferredWidth(8.dp))

        Button(onClick = { changeAlignContent(AlignContent.Center) }) {
          Text("Center")
        }
      }

      Spacer(modifier = Modifier.preferredHeight(8.dp))
      Row {
        Button(onClick = { changeAlignContent(AlignContent.Stretch) }) {
          Text("Stretch")
        }
      }

      Spacer(modifier = Modifier.preferredHeight(8.dp))
      Row {
        Button(onClick = { changeAlignContent(AlignContent.SpaceBetween) }) {
          Text("SpaceBetween")
        }
      }

      Spacer(modifier = Modifier.preferredHeight(8.dp))
      Row {
        Button(onClick = { changeAlignContent(AlignContent.SpaceAround) }) {
          Text("SpaceAround")
        }
      }
    }

    Text("AlignItems", style = MaterialTheme.typography.h4)
    Spacer(modifier = Modifier.preferredHeight(8.dp))
    Column {
      Row {
        Button(onClick = { changeAlignItems(AlignItems.FlexStart) }) {
          Text("FlexStart")
        }

        Spacer(modifier = Modifier.preferredWidth(8.dp))
        Button(onClick = { changeAlignItems(AlignItems.FlexEnd) }) {
          Text("FlexEnd")
        }

        Spacer(modifier = Modifier.preferredWidth(8.dp))
        Button(onClick = { changeAlignItems(AlignItems.Center) }) {
          Text("Center")
        }
      }

      Spacer(modifier = Modifier.preferredHeight(8.dp))
      Row {
        Button(onClick = { changeAlignItems(AlignItems.Stretch) }) {
          Text("Stretch")
        }

        Spacer(modifier = Modifier.preferredWidth(8.dp))
        Button(onClick = { changeAlignItems(AlignItems.Baseline) }) {
          Text("Baseline")
        }
      }
    }

    Spacer(modifier = Modifier.preferredHeight(60.dp))
  }
}

@Preview("sample app")
@Composable
fun PreviewSampleApp() {
  SampleApp()
}
