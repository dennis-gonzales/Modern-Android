package com.dnnsgnzls.modern.presentation.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dnnsgnzls.modern.presentation.ui.nav.BottomNavigation
import com.dnnsgnzls.modern.presentation.ui.nav.Screen
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme
import com.dnnsgnzls.modern.presentation.ui.views.GameListScreen
import com.dnnsgnzls.modern.presentation.ui.views.StoreListScreen


@Composable
fun ModernAndroidScaffold() {
    val navHostController = rememberNavController()
    val currentRoute = navHostController.currentBackStackEntryAsState().value?.destination?.route
    val ctx = LocalContext.current

    ModernAndroidTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    navController = navHostController,
                    currentRoute = currentRoute,
                    items = listOf(Screen.Games, Screen.Stores)
                )
            }
        ) { paddingValues ->
            NavHost(navController = navHostController, startDestination = Screen.Games.route) {
                composable(Screen.Stores.route) {
                    StoreListScreen()
                }
                composable(Screen.Games.route) { backStackEntry ->
                    GameListScreen(
                        navController = navHostController,
                        gamesViewModel = hiltViewModel(),
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}
