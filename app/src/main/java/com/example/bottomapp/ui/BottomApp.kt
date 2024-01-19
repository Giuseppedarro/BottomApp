package com.example.bottomapp.ui

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomapp.ui.navigation.NavGraph
import com.example.bottomapp.ui.navigation.NavScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomApp() {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        NavGraph(navController = navController, paddingValues = innerPadding)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf<NavScreens>(
        NavScreens.Homescreen,
        NavScreens.WorkoutScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    NavigationBar {
        screens.forEach { it ->
            NavigationBarItem(selected = (it.route == currentDestination), onClick = { navController.navigate(route = it.route) }, icon = { Icon(
                imageVector = it.icon,
                contentDescription = it.title
            ) })

        }
    }
}