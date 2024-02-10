package com.example.bottomapp.ui


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomapp.ui.navigation.BottomBar
import com.example.bottomapp.ui.navigation.NavDestination
import com.example.bottomapp.ui.navigation.NavGraph

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomApp() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = NavDestination.valueOf(navBackStackEntry?.destination?.route
        ?: NavDestination.HomeDestination.route)
    val bottomBarState = rememberSaveable { mutableStateOf(true) }

    bottomBarState.value = when (currentDestination) {
        NavDestination.SettingsDestination  -> false
        else -> true
    }

    Scaffold(
        bottomBar = { BottomBar(
            navController = navController,
            navBackStackEntry = navBackStackEntry,
            currentDestination  = currentDestination,
            bottomBarState = bottomBarState
        ) }
    ) { paddingValues ->
        NavGraph(navController = navController, paddingValues = paddingValues)
    }
}

