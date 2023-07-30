package com.dnnsgnzls.modern.presentation.ui.views.composables

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
    Button(
        onClick = { onToggleFavourite() },
        modifier = modifier.height(30.dp)
    ) {
        Crossfade(
            targetState = isFavourite,
            label = "Toggle Favourite Game"
        ) { isFavourite ->
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                when {
                    isFavourite -> {
                        Icon(
                            painter = painterResource(R.drawable.ic_favourite),
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = "My Favourite",
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }

                    else -> {
                        Icon(
                            painter = painterResource(R.drawable.ic_favourite_border),
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = "Set Favourite",
                            fontWeight = FontWeight.Normal,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                }
            }
        }
    }
}