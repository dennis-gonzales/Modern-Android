package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dnnsgnzls.modern.domain.model.Review
import com.dnnsgnzls.modern.framework.utils.Response

@Composable
fun ReviewsView(
    reviewsState: Response<List<Review>>,
    onDeleteReview: (Review) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        when (reviewsState) {
            is Response.Success -> {
                LazyColumn {
                    if (reviewsState.data.isEmpty()) {
                        item {
                            Text(
                                text = "You currently have no reviews, add one!",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    itemsIndexed(reviewsState.data) { index, review ->
                        Card(
                            shape = MaterialTheme.shapes.medium,
                            colors = CardDefaults.cardColors(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp, bottom = 4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Column(
                                    modifier = Modifier.weight(0.7f)
                                ) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "Review #${index + 1}",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(start = 6.dp)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Title: ${review.title}",
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Details: ${review.text}",
                                        style = MaterialTheme.typography.labelSmall,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                }

                                Box(
                                    modifier = Modifier
                                        .padding(
                                            top = 8.dp,
                                            start = 4.dp,
                                            end = 8.dp,
                                            bottom = 8.dp
                                        )
                                        .background(
                                            MaterialTheme.colorScheme.tertiary,
                                            shape = CircleShape,
                                        )
                                        .width(32.dp)
                                        .height(32.dp)
                                ) {
                                    IconButton(
                                        onClick = { onDeleteReview(review) },
                                    ) {
                                        Icon(
                                            Icons.Filled.Delete,
                                            contentDescription = "Delete review",
                                            tint = Color.Black
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            is Response.Error -> Text(text = "Error: ${reviewsState.exception.message}")
            is Response.Loading -> CircularProgressIndicator()
        }
    }
}