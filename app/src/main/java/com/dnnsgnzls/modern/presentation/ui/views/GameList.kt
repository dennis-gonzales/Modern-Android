package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dnnsgnzls.modern.domain.mock.Dota2
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Preview(showBackground = true)
@Composable
fun GameListPreview() {
    ModernAndroidTheme {
        GameList(
            Games(
                count = 3,
                next = null,
                previous = null,
                results = listOf(Dota2, Dota2, Dota2)
            )
        )
    }
}


@Composable
fun GameList(games: Games) {
    LazyColumn {
        items(games.results) { game ->
            GameItem(game)
        }
    }
}
