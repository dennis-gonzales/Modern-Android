package com.dnnsgnzls.modern.presentation.ui.nav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun BottomNavigation(
    navController: NavController,
    currentRoute: String?,
    items: List<Screen>
) {
    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.drawableResourceId),
                        contentDescription = stringResource(id = screen.stringResourceId)
                    )
                },
                label = {
                    Text(text = stringResource(id = screen.stringResourceId))
                },
                selected = currentRoute == screen.route,
                onClick = {
                    if(currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
