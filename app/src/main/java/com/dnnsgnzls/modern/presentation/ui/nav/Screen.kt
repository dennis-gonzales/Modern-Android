package com.dnnsgnzls.modern.presentation.ui.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dnnsgnzls.modern.R

sealed class Screen(
    val route: String,
    @StringRes val stringId: Int,
    @DrawableRes val drawableId: Int
) {
    object Stores : Screen(Destination.Stores, R.string.stores, R.drawable.ic_store)

    object Games : Screen(Destination.Games, R.string.stores, R.drawable.ic_game) {
        fun createRoute(storeId: Int): String = "${Args.StoreId}/${storeId}"
    }
}