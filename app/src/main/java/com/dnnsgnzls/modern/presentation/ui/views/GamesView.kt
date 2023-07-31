package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dnnsgnzls.modern.domain.mock.Dota2
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme
import com.dnnsgnzls.modern.presentation.ui.views.composables.GameList

@Preview(showBackground = true)
@Composable
fun PreviewGamesSuccessContent() {
    ModernAndroidTheme {
        GamesView(
            Response.Success(listOf(Dota2)),
            emptyList(),
            {},
            {},
            false,
            { _, _, _ -> }
        ) /* no-op for click */
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGamesErrorContent() {
    ModernAndroidTheme {
        GamesView(
            Response.Error(Exception("Test exception for preview!")),
            emptyList(),
            {},
            {}, false,
            { _, _, _ -> }
        ) /* no-op for click */
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGamesLoadingContent() {
    ModernAndroidTheme {
        GamesView(
            Response.Loading,
            emptyList(),
            {},
            {},
            false,
            { _, _, _ -> }
        ) /* no-op for click */
    }
}

@Composable
fun GamesView(
    gamesState: Response<List<Game>>,
    favouriteGameIds: List<Long>,
    onItemClick: (Game) -> Unit,
    onToggleFavourite: (Game) -> Unit = { },
    expandable: Boolean,
    onSaveReview: (Game, String, String) -> Unit = { _, _, _ -> }
) {
    when (gamesState) {
        is Response.Success -> GameList(
            games = gamesState.data,
            favouriteGameIds = favouriteGameIds,
            onItemClick = onItemClick,
            onToggleFavourite = onToggleFavourite,
            expandable = expandable,
            onSaveReview = onSaveReview
        )

        is Response.Loading -> CircularProgressIndicator()
        is Response.Error -> Text(text = "Error: ${gamesState.exception.message}")
    }
}
