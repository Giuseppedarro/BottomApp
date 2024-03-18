package com.example.bottomapp.workout.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bottomapp.workout.presentation.screens.AddExerciseScreen
import com.example.bottomapp.workout.presentation.screens.NewWorkoutScreen
import com.example.bottomapp.workout.presentation.screens.WorkoutScreen
import com.example.bottomapp.workout.presentation.viewmodels.CurrentSessionViewModel
import com.example.bottomapp.workout.presentation.viewmodels.WorkoutViewModel

const val WORKOUT_NAV_ROUTE = "workout_nav_route"

sealed class WorkoutNavDestination(
    val route: String
) {
    data object WorkoutDestination : WorkoutNavDestination(route = "workout_screen")
    data object NewWorkoutDestination : WorkoutNavDestination(route = "new_workout_screen")
    data object NewExerciseDestination: WorkoutNavDestination(route ="new_exercise_screen")
}

fun NavGraphBuilder.workoutNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    sessionViewModel: CurrentSessionViewModel,
    workoutViewModel: WorkoutViewModel
    ) {
    navigation(
        startDestination = WorkoutNavDestination.WorkoutDestination.route,
        route = WORKOUT_NAV_ROUTE
    ) {

        composable(
            route = WorkoutNavDestination.WorkoutDestination.route,
        ) {
            val workoutState by sessionViewModel.workoutState.collectAsState()
            WorkoutScreen(
                paddingValues = paddingValues,
                navigateToNewWorkout = {
                    sessionViewModel.updateWorkoutState(it)
                    navController.navigate(route = WorkoutNavDestination.NewWorkoutDestination.route)
                },
                sessionState = workoutState.sessionState,
                navigateToOldWorkout = { navController.navigate(WorkoutNavDestination.NewWorkoutDestination.route) },
                viewModel = workoutViewModel

            )
        }

        composable(route = WorkoutNavDestination.NewWorkoutDestination.route) {
            NewWorkoutScreen(
                navigateToExercises = { navController.navigate(WorkoutNavDestination.NewExerciseDestination.route) },
                paddingValues = paddingValues,
                addSet = sessionViewModel::addSetToExercise,
                sessionViewModel = sessionViewModel,
                saveWorkout = { workoutState -> workoutViewModel.saveWorkout(workoutState)},
                terminateWorkoutAndGoBack = {
                    sessionViewModel.terminateWorkout()
                    navController.navigateUp()
                }
            )
        }

        composable(route = WorkoutNavDestination.NewExerciseDestination.route) {
            AddExerciseScreen(
                sessionViewModel = sessionViewModel,
                addExerciseAndReturn = { exercise ->
                    sessionViewModel.addExerciseToWorkout(exercise)
                    navController.navigateUp()
                }
            )
        }
    }

}