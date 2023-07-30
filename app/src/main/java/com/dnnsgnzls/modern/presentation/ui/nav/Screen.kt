package com.dnnsgnzls.modern.presentation.ui.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dnnsgnzls.modern.R

sealed class Screen(
    val route: String,
    @StringRes val stringResourceId: Int,
    @DrawableRes val drawableResourceId: Int
) {

    object Games : Screen(Destination.Games, R.string.games, R.drawable.ic_game)

    object GameDetails : Screen(Destination.GameDetails, R.string.games, R.drawable.ic_game) {
        fun createRoute(gameId: Long): String = "game/$gameId"
    }

    object Favourites : Screen(Destination.Favourites, R.string.favourites, R.drawable.ic_favourite)
}
