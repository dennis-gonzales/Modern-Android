package com.dnnsgnzls.modern.presentation.ui.views.composables

import android.content.res.Configuration
import androidx.compose.foundation.clickable
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
import com.dnnsgnzls.modern.framework.constants.ButtonType
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewGameItem() {
    ModernAndroidTheme {
        GameItem(Dota2, true, {}, ButtonType.Favourites {}) /* no-op for click */
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewGameItemExpanded() {
    ModernAndroidTheme {
        Column {
            GameItem(Dota2, true, {}, ButtonType.ShowReviewForm {}) /* no-op for click */
            GameExpandedItem { _, _ -> } /* no-op for click */
        }
    }
}

@Composable
fun GameItem(
    game: Game,
    isFavourite: Boolean,
    onClick: (Game) -> Unit,
    buttonType: ButtonType
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable { onClick(game) }
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
                    .weight(1f)
            ) {

                when (buttonType) {
                    is ButtonType.Favourites -> {
                        FavouriteGameButton(isFavourite) {
                            buttonType.onToggleFavourite(game)
                        }
                    }

                    is ButtonType.ShowReviewForm -> {
                        GameButton(
                            text = "Add Review",
                            painter = painterResource(R.drawable.ic_reviews),
                            modifier = Modifier.height(30.dp)
                        ) {
                            buttonType.showReviewForm(game)
                        }
                    }
                }

                Text(
                    text = game.name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp)
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
                        text = if (game.rating != null && game.rating > 0) game.rating.toString() else "N/A",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}
