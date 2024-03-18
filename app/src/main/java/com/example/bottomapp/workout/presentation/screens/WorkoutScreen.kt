package com.example.bottomapp.workout.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.bottomapp.workout.domain.models.WorkoutState
import com.example.bottomapp.workout.presentation.components.NewAlertDialog
import com.example.bottomapp.workout.presentation.components.WorkoutSummary
import com.example.bottomapp.workout.presentation.viewmodels.WorkoutViewModel


@Composable
fun WorkoutScreen(
    paddingValues: PaddingValues,
    navigateToNewWorkout: (workout: WorkoutState) -> Unit,
    sessionState: Boolean,
    navigateToOldWorkout: () -> Unit,
    viewModel: WorkoutViewModel,
    ) {


    val workouts by viewModel.workoutsState.collectAsState()
    val scope = rememberCoroutineScope()
    val showNewAlertDialog = remember { mutableStateOf(false) }
    val chosenWorkout = remember { mutableStateOf(WorkoutState()) }

    if (showNewAlertDialog.value) {
        NewAlertDialog (
            onConfirm = { navigateToOldWorkout(); showNewAlertDialog.value = false},
            onDismiss = { navigateToNewWorkout(chosenWorkout.value); showNewAlertDialog.value = false },
            goBack = { showNewAlertDialog.value = false}
        )
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())

    ) {
        workouts.forEach{
            WorkoutSummary(
                workout = it,
                onDelete =  viewModel::deleteWorkout,
                onClick = { navigateToNewWorkout(it) },
                scope = scope,
                sessionState = sessionState,
                chosenWorkout = chosenWorkout,
                showNewAlertDialog = showNewAlertDialog
            )
        }
    }
    
}