package com.example.bottomapp.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.bottomapp.home.presentation.navigation.HOME_NAV_ROUTE
import com.example.bottomapp.home.presentation.navigation.homeNavGraph
import com.example.bottomapp.settings.presentation.navigation.settingsNavGraph
import com.example.bottomapp.workout.domain.models.WorkoutState
import com.example.bottomapp.workout.presentation.viewmodels.CurrentSessionViewModel
import com.example.bottomapp.workout.presentation.viewmodels.WorkoutViewModel
import com.example.bottomapp.workout.presentation.navigation.workoutNavGraph


@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    sessionViewModel: CurrentSessionViewModel,
    workoutState: WorkoutState,
    workoutViewModel: WorkoutViewModel
) {
    NavHost(
        navController = navController,
        startDestination = HOME_NAV_ROUTE,
        enterTransition = { slideInHorizontally(initialOffsetX = { fullWidth -> -30*fullWidth }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { fullWidth -> -30*fullWidth }) }

    ) {

        homeNavGraph(
            navController = navController,
            paddingValues = paddingValues,
            sessionViewModel = sessionViewModel,
            workoutState = workoutState,
            workoutViewModel = workoutViewModel
        )

        workoutNavGraph(
            navController = navController,
            paddingValues =  paddingValues,
            sessionViewModel =  sessionViewModel,
            workoutViewModel = workoutViewModel
        )

        settingsNavGraph(
            paddingValues = paddingValues,
            navController = navController
        )
    }
}