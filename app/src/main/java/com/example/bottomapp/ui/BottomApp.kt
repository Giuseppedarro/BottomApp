package com.example.bottomapp.ui


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomapp.model.WorkoutState
import com.example.bottomapp.ui.navigation.BottomBar
import com.example.bottomapp.ui.navigation.NavDestination
import com.example.bottomapp.ui.navigation.NavGraph
import com.example.bottomapp.ui.viewmodels.CurrentSessionViewModel
import com.example.bottomapp.ui.viewmodels.WorkoutViewModel
import org.koin.androidx.compose.getViewModel


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomApp() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = NavDestination.valueOf(navBackStackEntry?.destination?.route
        ?: NavDestination.HomeDestination.route)
    val bottomBarState = NavDestination.allDestinations.map{ it.route }
        .contains(navBackStackEntry?.destination?.route)
    val sessionViewModel: CurrentSessionViewModel = viewModel()
    val workoutViewModel: WorkoutViewModel = getViewModel()

    Scaffold(
        bottomBar = { BottomBar(
            navController = navController,
            currentDestination  = currentDestination,
            bottomBarState = bottomBarState
        ) }
    ) { paddingValues ->
        val workoutState: WorkoutState by sessionViewModel.workoutState.collectAsState()

        NavGraph(
            navController = navController,
            paddingValues = paddingValues,
            sessionViewModel = sessionViewModel,
            workoutState =  workoutState,
            workoutViewModel = workoutViewModel
        )
    }
}

