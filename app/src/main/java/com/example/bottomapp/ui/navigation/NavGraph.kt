package com.example.bottomapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bottomapp.R
import com.example.bottomapp.ui.screens.HomeScreen
import com.example.bottomapp.ui.screens.SettingsScreen
import com.example.bottomapp.ui.screens.WorkoutScreen

sealed class NavDestination(
    val route: String,
    val iconSelected: ImageVector,
    val iconNotSelected: ImageVector,
    @StringRes
    val title: Int
) {
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

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = NavDestination.HomeDestination.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { fullWidth -> 30*fullWidth }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { fullWidth -> -30*fullWidth }) }

    ) {
        composable(
            route = NavDestination.HomeDestination.route,

            ) {
            HomeScreen(paddingValues = paddingValues)
        }
        composable(
            route = NavDestination.WorkoutDestination.route,
            ) {
            WorkoutScreen(paddingValues = paddingValues)
        }
        composable(
            route = NavDestination.SettingsDestination.route,

            ) {
            SettingsScreen(paddingValues = paddingValues)
        }
    }
}