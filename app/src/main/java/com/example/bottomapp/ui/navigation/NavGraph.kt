package com.example.bottomapp.ui.navigation

import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bottomapp.ui.screens.HomeScreen
import com.example.bottomapp.ui.screens.WorkoutScreen

sealed class NavScreens(
    val route: String,
    val icon: ImageVector,
    val title: String = route
) {
    object Homescreen: NavScreens(
        route = "HomeScreen",
        icon = Icons.Filled.Home
    )
    object WorkoutScreen: NavScreens(
        route = "WorkoutScreen",
        icon = Icons.Filled.AccountBox
    )
}

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.Homescreen.route
    ) {
        composable(route = NavScreens.Homescreen.route) {
            HomeScreen()
        }
        composable(route = NavScreens.WorkoutScreen.route) {
            WorkoutScreen()
        }
    }
}