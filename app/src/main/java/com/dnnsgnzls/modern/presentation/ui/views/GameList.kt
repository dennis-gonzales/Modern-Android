package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dnnsgnzls.modern.domain.mock.Dota2
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Preview(showBackground = true)
@Composable
fun PreviewGameList() {
    ModernAndroidTheme {
        GameList(
            Games(
                count = 3,
                next = null,
                previous = null,
                results = listOf(Dota2, Dota2, Dota2)
            ),
            emptyList(),
            {},
            {}
        ) /* no-op for click */
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmptyGameList() {
    ModernAndroidTheme {
        GameList(
            Games(
                count = 0,
                next = null,
                previous = null,
                results = listOf()
            ),
            emptyList(),
            {},
            {}
        ) /* no-op for click */

    }
}


@Composable
fun GameList(
    games: Games,
    favouriteGameIds: List<Long>,
    onItemClick: (Game) -> Unit,
    onSaveGame: (Game) -> Unit
) {
    if (games.results.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "No games found",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        return
    }

    LazyColumn {
        items(games.results) { game ->
            GameItem(
                game = game,
                isFavourite = favouriteGameIds.contains(game.id),
                onClick = onItemClick,
                onSave = onSaveGame
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
