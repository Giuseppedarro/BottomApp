package com.example.bottomapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bottomapp.R
import com.example.bottomapp.ui.viewmodels.WorkoutViewModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch


@Composable
fun WorkoutScreen(paddingValues: PaddingValues) {

    val viewModel: WorkoutViewModel = viewModel(factory = WorkoutViewModel.Factory)
    val workoutsWithExercises by viewModel.workoutState.collectAsState()
    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card {
            Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
        }
        Button(onClick = { scope.launch(Dispatchers.IO) {
            viewModel.insertWorkoutWithExercises()
        } }) {
            Text(text = stringResource(id = R.string.insert))
        }
        workoutsWithExercises.forEach { workoutWithExercises ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(
                        id = R.string.workout_test,
                        workoutWithExercises.workout.name,
                        workoutWithExercises.workout.setsCount,
                        workoutWithExercises.exercises.first().exerciseName
                    )
                )
                Spacer(modifier = Modifier.weight(weight = 1f))
                IconButton(
                    onClick = { scope.launch { viewModel.deleteWorkoutWithExercises(workoutWithExercises) } }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }
            }

        }
    }


}