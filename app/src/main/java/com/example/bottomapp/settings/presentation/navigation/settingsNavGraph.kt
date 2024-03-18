package com.example.bottomapp.settings.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bottomapp.settings.presentation.screens.SettingsScreen

const val SETTINGS_NAV_ROUTE = "settings_nav_route"

sealed class SettingsNavDestination(
    val route: String
)  {
    data object SettingsDestination : SettingsNavDestination(route = "settings_destination")
}

fun NavGraphBuilder.settingsNavGraph(
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    navigation(
        startDestination = SettingsNavDestination.SettingsDestination.route,
        route = SETTINGS_NAV_ROUTE,
    ) {
        composable(route = SettingsNavDestination.SettingsDestination.route) {
            SettingsScreen(
                paddingValues = paddingValues,
                userName = "Giuseppe D'Arrò"
            )
        }
    }
}