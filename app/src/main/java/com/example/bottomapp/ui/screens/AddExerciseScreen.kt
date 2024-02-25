package com.example.bottomapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun AddExerciseScreen(addExerciseAndReturn: () -> Unit) {
    Column {
        Text(text = "exercises screen")
        Button(onClick = { addExerciseAndReturn() }) {
            Text(text = "torna")
        }
    }

}
