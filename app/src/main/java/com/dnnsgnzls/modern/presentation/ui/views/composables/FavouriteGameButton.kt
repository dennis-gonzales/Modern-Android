package com.dnnsgnzls.modern.presentation.ui.views.composables

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dnnsgnzls.modern.R
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Preview
@Composable
fun PreviewFavouriteGameButton() {
    ModernAndroidTheme {
        FavouriteGameButton(isFavourite = true) {} /* no-op for click */
    }
}

@Preview
@Composable
fun PreviewNonFavouriteGameButton() {
    ModernAndroidTheme {
        FavouriteGameButton(isFavourite = false) {} /* no-op for click */
    }
}

@Composable
fun FavouriteGameButton(
    isFavourite: Boolean,
    modifier: Modifier = Modifier,
    onToggleFavourite: () -> Unit
) {
    Crossfade(
        targetState = isFavourite,
        label = "Toggle Favourite Game",
        modifier = modifier
    ) { favourite ->
        when {
            favourite -> {
                GameButton(
                    text = "My Favourite",
                    painter = painterResource(R.drawable.ic_favourite),
                    modifier = Modifier.height(30.dp)
                ) {
                    onToggleFavourite()
                }
            }
            else -> {
                GameButton(
                    text = "Set Favourite",
                    painter = painterResource(R.drawable.ic_favourite_border),
                    modifier = Modifier.height(30.dp)
                ) {
                    onToggleFavourite()
                }
            }
        }
    }
}
