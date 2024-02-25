package com.example.bottomapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bottomapp.model.WorkoutState
import com.example.bottomapp.ui.screens.AddExerciseScreen
import com.example.bottomapp.ui.screens.NewWorkoutScreen
import com.example.bottomapp.ui.viewmodels.CurrentSessionViewModel

const val WORKOUT_NAV_ROUTE = "workout_nav_route"

sealed class WorkoutNavScreen(
    val route: String
) {
    data object NewWorkoutScreen : WorkoutNavScreen(route = "new_workout_screen")
    data object NewExerciseScreen: WorkoutNavScreen(route ="new_exercise_screen")
}

fun NavGraphBuilder.workoutNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    sessionViewModel: CurrentSessionViewModel,
    workoutState: WorkoutState
    ) {
    navigation(
        startDestination = WorkoutNavScreen.NewWorkoutScreen.route,
        route = WORKOUT_NAV_ROUTE
    ) {

        composable(route = WorkoutNavScreen.NewWorkoutScreen.route) {
            NewWorkoutScreen(
                navigateToExercises = { navController.navigate(WorkoutNavScreen.NewExerciseScreen.route) },
                paddingValues = paddingValues,
                workoutState = workoutState,
                addSet = sessionViewModel::addSetToExercise
            )
        }

        composable(route = WorkoutNavScreen.NewExerciseScreen.route) {
            AddExerciseScreen(
                addExerciseAndReturn = { navController.navigateUp() })
        }
    }

}