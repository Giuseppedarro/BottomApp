package com.example.bottomapp.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.bottomapp.model.WorkoutState
import com.example.bottomapp.ui.viewmodels.CurrentSessionViewModel
import com.example.bottomapp.ui.viewmodels.WorkoutViewModel


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
        startDestination = MAIN_NAV_ROUTE,
        enterTransition = { slideInHorizontally(initialOffsetX = { fullWidth -> -30*fullWidth }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { fullWidth -> -30*fullWidth }) }

    ) {

        mainNavGraph(
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

    }
}