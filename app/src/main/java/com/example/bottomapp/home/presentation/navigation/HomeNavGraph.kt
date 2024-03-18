package com.example.bottomapp.home.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bottomapp.workout.domain.models.WorkoutState
import com.example.bottomapp.home.presentation.screens.HomeScreen
import com.example.bottomapp.workout.presentation.viewmodels.CurrentSessionViewModel
import com.example.bottomapp.workout.presentation.viewmodels.WorkoutViewModel

const val HOME_NAV_ROUTE = "home_nav_route"

sealed class HomeNavDestination(
    val  route: String
) {
    data object HomeDestination : HomeNavDestination(route = "home_destination")
}

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    sessionViewModel: CurrentSessionViewModel,
    workoutState: WorkoutState,
    workoutViewModel: WorkoutViewModel
) {
    navigation(
        startDestination = HomeNavDestination.HomeDestination.route,
        route = HOME_NAV_ROUTE
    ) {
        composable(
            route = HomeNavDestination.HomeDestination.route,
        ) {
            HomeScreen(paddingValues = paddingValues)
        }
    }

}