package com.dnnsgnzls.modern.presentation.ui.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dnnsgnzls.modern.R
import com.dnnsgnzls.modern.domain.mock.Dota2
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewGameItem() {
    ModernAndroidTheme {
        GameItem(Dota2)
    }
}


@Composable
fun GameItem(game: Game) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = game.backgroundImage,
                contentDescription = game.name,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_game)
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = game.name ?: "N/A",
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Released: ${game.released}",
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = "Rating",
                        tint = Color.Yellow
                    )
                    Text(
                        text = game.rating?.toString() ?: "N/A",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}
