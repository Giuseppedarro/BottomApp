package com.example.bottomapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.bottomapp.model.ExerciseState
import com.example.bottomapp.ui.viewmodels.CurrentSessionViewModel


@Composable
fun AddExerciseScreen(
    addExerciseAndReturn: (exercise: ExerciseState) -> Unit,
    sessionViewModel: CurrentSessionViewModel
) {
    val allExercises = sessionViewModel.allExercises

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        allExercises.forEach { exercise ->
            Text(text = exercise.exerciseName)
            Button(onClick = { addExerciseAndReturn(exercise) }) {
                Text(text = "add")
            }
        }

    }

}
