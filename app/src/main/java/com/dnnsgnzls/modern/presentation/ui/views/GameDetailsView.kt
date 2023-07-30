package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.presentation.ui.views.composables.FavouriteGameButton
import com.dnnsgnzls.modern.presentation.ui.views.composables.GameInfo

@Composable
fun GameDetailsView(
    gameState: Response<Game>,
    isFavourite: Boolean,
    onBack: () -> Unit,
    onToggleFavourite: (Game) -> Unit,
) {
    when (gameState) {
        is Response.Success -> {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                BackButton(Modifier.weight(0.4f)) { onBack() }
                Spacer(modifier = Modifier.size(4.dp))
                FavouriteGameButton(isFavourite, Modifier.weight(0.6f)) {
                    onToggleFavourite(gameState.data)
                }
            }

            GameInfo(game = gameState.data, modifier = Modifier.padding(16.dp))
        }

        is Response.Error -> Text(text = "Error: ${gameState.exception.message}")
        is Response.Loading -> CircularProgressIndicator()
    }
}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Button(
        onClick = { onBack() },
        modifier = modifier.height(30.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.ArrowBack, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Go Back",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}