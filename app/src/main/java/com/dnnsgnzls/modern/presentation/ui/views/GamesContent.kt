package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dnnsgnzls.modern.domain.mock.Dota2
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme
import java.lang.Exception

@Preview(showBackground = true)
@Composable
fun PreviewSucessContent() {
    val games = Games(
        count = 1,
        next = null,
        previous = null,
        results = listOf(Dota2)
    )

    ModernAndroidTheme {
        GamesContent(Response.Success(games))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewErrorContent() {
    ModernAndroidTheme {
        GamesContent(Response.Error(Exception("Test exception for preview!")))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingContent() {
    ModernAndroidTheme {
        GamesContent(Response.Loading)
    }
}

@Composable
fun GamesContent(gamesState: Response<Games>) {
    when (gamesState) {
        is Response.Success -> GameList(games = gamesState.data)
        is Response.Loading -> CircularProgressIndicator()
        is Response.Error -> Text(text = "Error: ${gamesState.exception.message}")
    }
}
