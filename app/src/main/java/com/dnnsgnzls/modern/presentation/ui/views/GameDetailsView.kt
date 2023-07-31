package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dnnsgnzls.modern.domain.mock.Dota2
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.model.Review
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme
import com.dnnsgnzls.modern.presentation.ui.views.composables.BackButton
import com.dnnsgnzls.modern.presentation.ui.views.composables.FavouriteGameButton
import com.dnnsgnzls.modern.presentation.ui.views.composables.GameInfo


@Preview(showBackground = true)
@Composable
fun PreviewGameDetailsSuccessContent() {
    ModernAndroidTheme {
        GameDetailsView(
            gameState = Response.Success(Dota2),
            reviewsState = Response.Success(
                listOf(Review(-1, "Test Review", "Review Details"))
            ),
            isFavourite = true,
            onBack = {}, /* no-op for click */
            onToggleFavourite = {}, /* no-op for click */
            onDeleteReview = {}  /* no-op for click */
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameDetailsErrorContent() {
    ModernAndroidTheme {
        GameDetailsView(
            Response.Error(Exception("Test exception for preview!")),
            Response.Error(Exception("Test exception for preview!")),
            isFavourite = true,
            onBack = {}, /* no-op for click */
            onToggleFavourite = {}, /* no-op for click */
            onDeleteReview = {}  /* no-op for click */
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameDetailsLoadingContent() {
    ModernAndroidTheme {
        GameDetailsView(
            gameState = Response.Loading,
            reviewsState = Response.Loading,
            isFavourite = true,
            onBack = {}, /* no-op for click */
            onToggleFavourite = {}, /* no-op for click */
            onDeleteReview = {}  /* no-op for click */
        )
    }
}

@Composable
fun GameDetailsView(
    gameState: Response<Game>,
    reviewsState: Response<List<Review>>,
    isFavourite: Boolean,
    onBack: () -> Unit,
    onToggleFavourite: (Game) -> Unit,
    onDeleteReview: (Review) -> Unit,
) {
    when (gameState) {
        is Response.Success -> {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    BackButton(
                        modifier = Modifier
                            .weight(0.4f)
                            .height(30.dp)
                    ) { onBack() }

                    Spacer(modifier = Modifier.size(4.dp))

                    FavouriteGameButton(isFavourite, modifier = Modifier.weight(0.6f)) {
                        onToggleFavourite(gameState.data)
                    }
                }

                GameInfo(game = gameState.data, modifier = Modifier.padding(16.dp))

                ReviewsView(
                    reviewsState,
                    modifier = Modifier.padding(16.dp),
                    onDeleteReview = onDeleteReview
                )
            }
        }

        is Response.Error -> Text(text = "Error: ${gameState.exception.message}")
        is Response.Loading -> CircularProgressIndicator()
    }
}

