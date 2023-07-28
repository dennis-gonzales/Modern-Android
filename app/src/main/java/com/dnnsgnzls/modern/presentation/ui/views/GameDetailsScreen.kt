package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Composable
fun GameDetailsScreen(game: Game, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { onClick() },
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
//        color = MaterialTheme.colors.onBackground
    )
}

@Composable
fun GameReleaseDate(releaseDate: String) {
    Text(
        text = "Released: $releaseDate",
        style = MaterialTheme.typography.labelSmall,
//        color = MaterialTheme.colors.onBackground
    )
}

@Composable
fun GameRating(rating: Double?) {
    rating?.let {
        Text(
            text = "Rating: ${"%.1f".format(it)} / 5",
            style = MaterialTheme.typography.labelSmall,
//            color = MaterialTheme.colors.onBackground
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewGameDetailsScreen() {
    ModernAndroidTheme {
        val game = Game(
            id = 1L,
            slug = "slug",
            name = "Game Name",
            released = "2013-07-09",
            backgroundImage = "https://media.rawg.io/media/games/6fc/6fcf4cd3b17c288821388e6085bb0fc9.jpg",
            rating = 4.0
        )
        GameDetailsScreen(game) {} /* no-op for click */
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
