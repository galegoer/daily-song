package com.mongodb.app.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.mongodb.app.R
import com.mongodb.app.data.MockRepository
import com.mongodb.app.presentation.tasks.AddItemViewModel
import com.mongodb.app.ui.theme.MyApplicationTheme
import com.mongodb.app.ui.theme.Purple200
import androidx.compose.material3.Text


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemPrompt(viewModel: AddItemViewModel) {
    Card {
        Text("Hello")
        var expanded by remember { mutableStateOf(false) }
        Column(Modifier.clickable { expanded = !expanded }) {
            AsyncImage(
                model = "https://m.media-amazon.com/images/I/71VjFFfuJ4L._AC_SL1050_.jpg",
                contentDescription = "Album cover"
            )
            AnimatedVisibility(expanded) {
                Text(
                    text = "Testing",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}
//        containerColor = Color.White,
//        title = { Text(stringResource(R.string.add_item)) },
//        text = {
//            Column {
//                Text("Testing")
//                TextField(
//                    colors = ExposedDropdownMenuDefaults.textFieldColors(containerColor = Color.White),
//                    value = viewModel.taskSummary.value,
//                    maxLines = 2,
//                    onValueChange = {
//                        viewModel.updateTaskSummary(it)
//                    },
//                    label = { Text(stringResource(R.string.item_summary)) }
//                )
//            }
//        },
//        confirmButton = {
//            Button(
//                colors = buttonColors(containerColor = Purple200),
//                onClick = {
//                    viewModel.addTask()
//                }
//            ) {
//                Text(stringResource(R.string.listen_now))
//            }
//        },
//        dismissButton = {
//            Button(
//                colors = buttonColors(containerColor = Purple200),
//                onClick = {
//                    viewModel.closeAddTaskDialog()
//                }
//            ) {
//                Text(stringResource(R.string.cancel))
//            }
//        },



@Preview(showBackground = true)
@Composable
fun AddItemPreview() {
    MyApplicationTheme {
        MyApplicationTheme {
            val repository = MockRepository()
            val viewModel = AddItemViewModel(repository)
            AddItemPrompt(viewModel)
        }
    }
}
