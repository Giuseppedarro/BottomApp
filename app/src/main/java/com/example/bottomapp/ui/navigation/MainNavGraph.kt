package com.example.bottomapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bottomapp.R
import com.example.bottomapp.model.WorkoutState
import com.example.bottomapp.ui.screens.HomeScreen
import com.example.bottomapp.ui.screens.SettingsScreen
import com.example.bottomapp.ui.screens.WorkoutScreen
import com.example.bottomapp.ui.viewmodels.CurrentSessionViewModel

const val MAIN_NAV_ROUTE = "main_nav_route"

sealed class NavDestination(
    val route: String,
    val iconSelected: ImageVector,
    val iconNotSelected: ImageVector,
    @StringRes
    val title: Int
)  {
    data object HomeDestination: NavDestination(
        route = "home_screen",
        iconSelected = Icons.Filled.Home,
        iconNotSelected = Icons.Outlined.Home,
        title = R.string.home_screen
    )
    data object WorkoutDestination: NavDestination(
        route = "workout_screen",
        iconSelected = Icons.Filled.AccountBox,
        iconNotSelected = Icons.Outlined.AccountBox,
        title = R.string.workout_screen

    )
    data object SettingsDestination: NavDestination(
        route = "settings_screen",
        iconSelected = Icons.Filled.Settings,
        iconNotSelected = Icons.Outlined.Settings,
        title = R.string.settings_screen
    )
    companion object {
        val allDestinations = listOf(
            HomeDestination,
            WorkoutDestination,
            SettingsDestination
        )
        /**Function that returns NavDestination of the given route*/
        fun valueOf(route: String) = allDestinations.find{ it.route == route } ?: HomeDestination
    }
}

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    sessionViewModel: CurrentSessionViewModel,
    workoutState: WorkoutState
    ) {
    navigation(
        startDestination = NavDestination.HomeDestination.route,
        route = MAIN_NAV_ROUTE
    ) {
        composable(
            route = NavDestination.HomeDestination.route,
        ) {
            HomeScreen(paddingValues = paddingValues)
        }
        composable(
            route = NavDestination.WorkoutDestination.route,
        ) {
            WorkoutScreen(
                paddingValues = paddingValues,
                navigateToNewWorkout = {
                    sessionViewModel.updateWorkoutState(it)
                    navController.navigate(route = WORKOUT_NAV_ROUTE)
                },
                sessionState = workoutState.sessionState,
                navigateToOldWorkout = { navController.navigate(WORKOUT_NAV_ROUTE) }
            )
        }
        composable(
            route = NavDestination.SettingsDestination.route,

            ) {
            SettingsScreen(paddingValues = paddingValues)
        }
    }

}