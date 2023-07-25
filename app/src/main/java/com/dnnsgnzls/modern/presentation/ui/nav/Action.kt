package com.dnnsgnzls.modern.presentation.ui.nav

import androidx.navigation.NavController

class Action(navController: NavController) {
    val stores: () -> Unit = { navController.navigate(Screen.Stores.route) }

    val games: (storeId: Int) -> Unit = { storeId ->
        navController.navigate(Screen.Games.createRoute(storeId))
    }
}