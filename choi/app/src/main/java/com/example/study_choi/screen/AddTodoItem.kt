package com.example.study_choi.screen

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.study_choi.ui.theme.Study_choiTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoItem(navController: NavHostController) {
    val openTimeDialog = remember { mutableStateOf(false) }
    val openDateDialog = remember { mutableStateOf(false) }

    val deadLineDate = remember { mutableStateOf("날짜") }
    val deadLineTime = remember { mutableStateOf("시간") }

    val titleTextState = remember { mutableStateOf("") }
    val descriptionTextState = remember { mutableStateOf("") }

    Column {
        androidx.compose.material3.TopAppBar(
            title = { Text("Todo List 추가") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, "Cancel")
                }
            }
        )
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                label = { Text(text = "Title") },
                value = titleTextState.value,
                onValueChange = { titleTextState.value = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                label = { Text(text = "Description") },
                value = descriptionTextState.value,
                onValueChange = { descriptionTextState.value = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 32.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {

                Button(onClick = {
                    openDateDialog.value = true
                }) {
                    Text(text = deadLineDate.value)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = {
                    openTimeDialog.value = true
                }) {
                    Text(text = deadLineTime.value)
                }
            }

            if (openDateDialog.value) {
                DeadlineDatePickerDialog(
                    openDateDialog = openDateDialog,
                    deadLineDate = deadLineDate
                )
            }

            if (openTimeDialog.value) {
                DeadLineTimePickerDialog(
                    openTimeDialog = openTimeDialog,
                    deadLineTime = deadLineTime)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeadLineTimePickerDialog(
    openTimeDialog: MutableState<Boolean>,
    deadLineTime: MutableState<String>
) {
    val timePickerState = rememberTimePickerState()

    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(size = 12.dp)
            ),
        onDismissRequest = { openTimeDialog.value = false }
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = Color.LightGray.copy(alpha = 0.3f)
                )
                .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimePicker(state = timePickerState)

            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { openTimeDialog.value = false }) {
                    Text(text = "Dismiss")
                }

                TextButton(
                    onClick = {
                        openTimeDialog.value = false
                        deadLineTime.value = "${timePickerState.hour}:${timePickerState.minute}"
                    }
                ) {
                    Text(text = "Confirm")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeadlineDatePickerDialog(
    openDateDialog: MutableState<Boolean>,
    deadLineDate: MutableState<String>
) {
    val datePickerState = rememberDatePickerState()
    val confirmEnabled = remember {
        derivedStateOf { datePickerState.selectedDateMillis != null }
    }
    DatePickerDialog(
        onDismissRequest = { openDateDialog.value = false },
        confirmButton = {
            TextButton(
                onClick = {
                    openDateDialog.value = false
                    val selectedDate = datePickerState.selectedDateMillis?.let {
                        convertMillisToDate(it)
                    } ?: ""
                    deadLineDate.value = selectedDate
                },
                enabled = confirmEnabled.value
            ) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDateDialog.value = false
                }
            ) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return formatter.format(Date(millis))
}

@Preview
@Composable
fun previewAddTodo() {
    Study_choiTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AddTodoItem(navController = rememberNavController())
        }
    }
}