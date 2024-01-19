package com.example.bottomapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bottomapp.ui.screens.HomeScreen
import com.example.bottomapp.ui.screens.SettingsScreen
import com.example.bottomapp.ui.screens.WorkoutScreen

sealed class NavScreens(
    val route: String,
    val icon: ImageVector,
    val title: String = route
) {
    data object HomeScreen: NavScreens(
        route = "HomeScreen",
        icon = Icons.Filled.Home
    )
    data object WorkoutScreen: NavScreens(
        route = "WorkoutScreen",
        icon = Icons.Filled.AccountBox
    )
    data object SettingsScreen: NavScreens(
        route = "SettingsScreen",
        icon = Icons.Filled.Settings
    )
}

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.HomeScreen.route
    ) {
        composable(route = NavScreens.HomeScreen.route) {
            HomeScreen()
        }
        composable(route = NavScreens.WorkoutScreen.route) {
            WorkoutScreen()
        }
        composable(route = NavScreens.SettingsScreen.route) {
            SettingsScreen()
        }
    }
}