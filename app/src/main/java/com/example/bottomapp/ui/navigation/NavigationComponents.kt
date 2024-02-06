package com.example.bottomapp.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

@Composable
fun BottomBar(
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry?,
    currentDestination: String?,
    bottomBarState: MutableState<Boolean>

) {

    val screens = listOf(
        NavScreens.HomeScreen,
        NavScreens.WorkoutScreen,
        NavScreens.SettingsScreen
    )

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        NavigationBar {
            screens.forEach { it ->
                NavigationBarItem(
                    selected = (it.route == currentDestination),
                    enabled = (it.route != currentDestination),
                    onClick = { navController.navigate(route = it.route) },
                    icon = { Icon(
                        imageVector = if (it.route == currentDestination)
                            it.iconSelected else it.iconNotSelected,
                        contentDescription = stringResource(id = it.title)
                    )
                           },
                    label = { Text(text = stringResource(id = it.title)) }
                )

            }
        }
    }

}