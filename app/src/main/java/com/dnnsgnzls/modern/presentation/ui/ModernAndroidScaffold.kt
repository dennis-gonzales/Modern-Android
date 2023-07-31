package com.dnnsgnzls.modern.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dnnsgnzls.modern.presentation.ui.nav.BottomNavigation
import com.dnnsgnzls.modern.presentation.ui.nav.Screen
import com.dnnsgnzls.modern.presentation.ui.screens.BusinessCardScreen
import com.dnnsgnzls.modern.presentation.ui.screens.FavouritesScreen
import com.dnnsgnzls.modern.presentation.ui.screens.GameDetailsScreen
import com.dnnsgnzls.modern.presentation.ui.screens.GameListScreen
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme
import com.dnnsgnzls.modern.presentation.viewmodels.GamesViewModel


@Composable
fun ModernAndroidScaffold() {
    val gamesViewModel: GamesViewModel = hiltViewModel()
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val snackbarHostState = remember { SnackbarHostState() }
    val ctx = LocalContext.current
    val enter = slideInVertically(initialOffsetY = { it }) + fadeIn(initialAlpha = 0.3f)
    val exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()

    ModernAndroidTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                AnimatedVisibility(
                    visible =
                    currentRoute != Screen.GameDetails.route
                            && currentRoute != Screen.BusinessCardScreen.route,
                    enter = enter,
                    exit = exit
                ) {
                    BottomNavigation(
                        navController = navController,
                        currentRoute = currentRoute,
                        items = listOf(
                            Screen.Games,
                            Screen.Favourites,
                            Screen.BusinessCardScreen
                        )
                    )
                }
            }
        ) { paddingValues ->
            NavHost(navController = navController, startDestination = Screen.Games.route) {
                composable(Screen.Games.route) {
                    GameListScreen(
                        gamesViewModel = gamesViewModel,
                        navController = navController,
                        snackBarHostState = snackbarHostState,
                        paddingValues = paddingValues
                    )
                }
                composable(Screen.GameDetails.route) { backStackEntry ->
                    val gameId = backStackEntry.arguments?.getString("gameId")

                    GameDetailsScreen(
                        gameId = gameId,
                        gamesViewModel = gamesViewModel,
                        navController = navController,
                        snackBarHostState = snackbarHostState,
                        paddingValues = paddingValues
                    )
                }
                composable(Screen.Favourites.route) { backStackEntry ->
                    FavouritesScreen(
                        gamesViewModel = gamesViewModel,
                        navController = navController,
                        snackBarHostState = snackbarHostState,
                        paddingValues = paddingValues
                    )
                }

                composable(Screen.BusinessCardScreen.route) {
                    BusinessCardScreen(
                        modifier = Modifier.padding(
                            bottom = paddingValues.calculateBottomPadding()
                        )
                    )

                }
            }
        }
    }
}
