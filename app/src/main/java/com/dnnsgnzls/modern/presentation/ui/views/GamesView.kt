package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dnnsgnzls.modern.domain.mock.Dota2
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme
import com.dnnsgnzls.modern.presentation.ui.views.composables.GameList
import java.lang.Exception

@Preview(showBackground = true)
@Composable
fun PreviewSuccessContent() {
    val games = Games(
        count = 1,
        next = null,
        previous = null,
        results = listOf(Dota2)
    )

    ModernAndroidTheme {
        GamesView(Response.Success(games), emptyList(), {}, {}) /* no-op for click */
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewErrorContent() {
    ModernAndroidTheme {
        GamesView(
            Response.Error(Exception("Test exception for preview!")),
            emptyList(),
            {},
            {}) /* no-op for click */
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingContent() {
    ModernAndroidTheme {
        GamesView(Response.Loading, emptyList(), {}, {}) /* no-op for click */
    }
}

@Composable
fun GamesView(
    gamesState: Response<Games>,
    favouriteGameIds: List<Long>,
    onItemClick: (Game) -> Unit,
    onToggleFavourite: (Game) -> Unit
) {
    when (gamesState) {
        is Response.Success -> GameList(gamesState.data, favouriteGameIds, onItemClick, onToggleFavourite)
        is Response.Loading -> CircularProgressIndicator()
        is Response.Error -> Text(text = "Error: ${gamesState.exception.message}")
    }
}
