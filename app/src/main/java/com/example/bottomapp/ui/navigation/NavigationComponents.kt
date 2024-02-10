package com.example.bottomapp.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
    currentDestination: NavDestination,
    bottomBarState: MutableState<Boolean>

) {
    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        NavigationBar {
            NavDestination.allDestinations.forEach { it ->
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