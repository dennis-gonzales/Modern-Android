package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme
import com.dnnsgnzls.modern.presentation.viewmodels.GamesViewModel

@Composable
fun GameDetailsScreen( // TODO: Decompose into smaller composable functinos
    gameId: String?,
    gamesViewModel: GamesViewModel,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues
) {
    val favouriteGameIdsState: Response<List<Long>> by gamesViewModel.favouriteGameIds.collectAsStateWithLifecycle()
    val gameState: Response<Game> by gamesViewModel.game.collectAsStateWithLifecycle()

    if (gameId == null) {
        Text(
            text = "There was problem loading that game, please try again later.",
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
        return
    }

    LaunchedEffect(key1 = Unit) {
        gamesViewModel.fetchSingleGame(gameId)
    }

    when (val gameCpy = gameState) {
        is Response.Success -> {
            val game = gameCpy.data
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    ),
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Go Back", style = MaterialTheme.typography.labelSmall)
                    }
                }

                GameBackgroundImage(game.backgroundImage ?: "")
                Spacer(modifier = Modifier.height(16.dp))
                GameName(game.name)
                Spacer(modifier = Modifier.height(8.dp))
                GameReleaseDate(game.released ?: "")
                Spacer(modifier = Modifier.height(8.dp))
                GameRating(game.rating)
            }
        }

        is Response.Error -> Text(text = "Error: ${gameCpy.exception.message}")
        is Response.Loading -> CircularProgressIndicator()
    }

}

@Composable
fun GameBackgroundImage(url: String) {
    val image: Painter = rememberAsyncImagePainter(model = url)

    Image(
        painter = image,
        contentDescription = "Game background image",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun GameName(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun GameReleaseDate(releaseDate: String) {
    Text(
        text = "Released: $releaseDate",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun GameRating(rating: Double?) {
    rating?.let {
        Text(
            text = "Rating: ${"%.1f".format(it)} / 5",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameBackgroundImage() {
    ModernAndroidTheme {
        GameBackgroundImage("https://media.rawg.io/media/games/6fc/6fcf4cd3b17c288821388e6085bb0fc9.jpg")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameName() {
    ModernAndroidTheme {
        GameName("Game Name")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameReleaseDate() {
    ModernAndroidTheme {
        GameReleaseDate("2013-07-09")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameRating() {
    ModernAndroidTheme {
        GameRating(4.0)
    }
}
