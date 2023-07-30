package com.dnnsgnzls.modern.presentation.ui.views.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dnnsgnzls.modern.domain.mock.Dota2
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme


@Preview(showBackground = true)
@Composable
fun PreviewGameInfo() {
    ModernAndroidTheme {
        GameInfo(game = Dota2)
    }
}
@Composable
fun GameInfo(
    game: Game,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        GameBackgroundImage(game.backgroundImage ?: "")
        Spacer(modifier = Modifier.height(16.dp))
        GameName(game.name)
        Spacer(modifier = Modifier.height(8.dp))
        GameReleaseDate(game.released ?: "")
        Spacer(modifier = Modifier.height(8.dp))
        GameRating(game.rating)
        Spacer(modifier = Modifier.height(16.dp))
    }
}