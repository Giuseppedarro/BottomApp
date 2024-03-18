package com.example.bottomapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.bottomapp.R
import com.example.bottomapp.home.presentation.navigation.HomeNavDestination
import com.example.bottomapp.settings.presentation.navigation.SettingsNavDestination
import com.example.bottomapp.workout.presentation.navigation.WorkoutNavDestination

sealed class NavDestination(
    val route: String,
    val iconSelected: ImageVector,
    val iconNotSelected: ImageVector,
    @StringRes
    val title: Int
)  {
    data object HomeDestination: NavDestination(
        route = HomeNavDestination.HomeDestination.route,
        iconSelected = Icons.Filled.Home,
        iconNotSelected = Icons.Outlined.Home,
        title = R.string.home_screen
    )
    data object WorkoutDestination: NavDestination(
        route = WorkoutNavDestination.WorkoutDestination.route,
        iconSelected = Icons.Filled.AccountBox,
        iconNotSelected = Icons.Outlined.AccountBox,
        title = R.string.workout_screen

    )
    data object SettingsDestination: NavDestination(
        route = SettingsNavDestination.SettingsDestination.route,
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
fun BottomBar(
    navController: NavHostController,
    currentDestination: NavDestination,
    bottomBarState: Boolean

) {
    AnimatedVisibility(
        visible = bottomBarState,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        NavigationBar {
            NavDestination.allDestinations.forEach {
                NavigationBarItem(
                    selected = (it.route == currentDestination.route),
                    enabled = (it.route != currentDestination.route),
                    onClick = { navController.navigate(route = it.route) },
                    icon = { Icon(
                        imageVector = if (it.route == currentDestination.route)
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