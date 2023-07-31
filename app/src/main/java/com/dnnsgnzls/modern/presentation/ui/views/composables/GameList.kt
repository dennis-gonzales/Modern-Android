package com.dnnsgnzls.modern.presentation.ui.views.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dnnsgnzls.modern.domain.mock.Dota2
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.framework.constants.ButtonType
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Preview(showBackground = true)
@Composable
fun PreviewGameList() {
    ModernAndroidTheme {
        GameList(
            listOf(Dota2, Dota2, Dota2),
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
fun PreviewEmptyGameList() {
    ModernAndroidTheme {
        GameList(
            listOf(),
            emptyList(),
            {},
            {},
            false,
            { _, _, _ -> }
        ) /* no-op for click */
    }
}


@Composable
fun GameList(
    games: List<Game>,
    favouriteGameIds: List<Long>,
    onItemClick: (Game) -> Unit,
    onToggleFavourite: (Game) -> Unit,
    expandable: Boolean,
    onSaveReview: (Game, String, String) -> Unit
) {
    if (games.isEmpty()) {
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

    var expandedGameId by remember { mutableStateOf<Long?>(null) }

    LazyColumn {
        items(games) { game ->
            GameItem(
                game = game,
                isFavourite = favouriteGameIds.contains(game.id),
                onClick = { onItemClick(game) },
                buttonType =
                if (expandable) ButtonType.ShowReviewForm {
                    if (expandedGameId == it.id) {
                        expandedGameId = null
                    } else {
                        expandedGameId = it.id
                    }
                }
                else ButtonType.Favourites { onToggleFavourite(it) }
            )

            AnimatedVisibility(
                visible = expandable && expandedGameId == game.id,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                GameExpandedItem { title, details ->
                    expandedGameId = null
                    onSaveReview(game, title, details)
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
