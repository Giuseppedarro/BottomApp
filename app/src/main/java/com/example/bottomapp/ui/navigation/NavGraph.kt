package com.example.bottomapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
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

sealed class NavScreens(
    val route: String,
    val iconSelected: ImageVector,
    val iconNotSelected: ImageVector,
    @StringRes
    val title: Int
) {
    data object HomeScreen: NavScreens(
        route = "home_screen",
        iconSelected = Icons.Filled.Home,
        iconNotSelected = Icons.Outlined.Home,
        title = R.string.home_screen
    )
    data object WorkoutScreen: NavScreens(
        route = "workout_screen",
        iconSelected = Icons.Filled.AccountBox,
        iconNotSelected = Icons.Outlined.AccountBox,
        title = R.string.workout_screen
    )
    data object SettingsScreen: NavScreens(
        route = "settings_screen",
        iconSelected = Icons.Filled.Settings,
        iconNotSelected = Icons.Outlined.Settings,
        title = R.string.settings_screen
    )
}

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.HomeScreen.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { fullWidth -> 30*fullWidth }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { fullWidth -> -30*fullWidth }) }

    ) {
        composable(
            route = NavScreens.HomeScreen.route,

            ) {
            HomeScreen()
        }
        composable(
            route = NavScreens.WorkoutScreen.route,
            ) {
            WorkoutScreen()
        }
        composable(
            route = NavScreens.SettingsScreen.route,

            ) {
            SettingsScreen()
        }
    }
}