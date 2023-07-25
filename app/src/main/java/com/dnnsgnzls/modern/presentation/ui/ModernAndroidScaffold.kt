package com.dnnsgnzls.modern.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dnnsgnzls.modern.presentation.ui.nav.Action
import com.dnnsgnzls.modern.presentation.ui.nav.Args
import com.dnnsgnzls.modern.presentation.ui.nav.Screen
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme
import com.dnnsgnzls.modern.presentation.ui.views.GameListScreen
import com.dnnsgnzls.modern.presentation.ui.views.StoreListScreen


@Composable
fun ModernAndroidScaffold() {
    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }
    val ctx = LocalContext.current

    ModernAndroidTheme {
        Scaffold(
            bottomBar = { }
        ) { PaddingValues ->
            val modifier = Modifier.padding(PaddingValues)

            NavHost(navController = navController, startDestination = Screen.Stores.route) {
                composable(Screen.Stores.route) {
                    StoreListScreen()
                }
                composable(Screen.Games.route) { backStackEntry ->
                    val storeId = backStackEntry.arguments?.getInt(Args.StoreId)
                    GameListScreen()
                }
            }
        }
    }
}

