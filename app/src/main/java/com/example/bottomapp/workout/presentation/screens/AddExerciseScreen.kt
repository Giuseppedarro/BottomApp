package com.example.bottomapp.workout.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.bottomapp.workout.domain.models.ExerciseState
import com.example.bottomapp.workout.presentation.viewmodels.CurrentSessionViewModel


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
            .verticalScroll(state = rememberScrollState())
    ) {
        allExercises.forEach { exercise ->
            Text(text = exercise.exerciseName)
            Button(onClick = { addExerciseAndReturn(exercise) }) {
                Text(text = "add")
            }
        }

    }

}