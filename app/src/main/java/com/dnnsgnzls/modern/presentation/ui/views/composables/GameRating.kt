package com.dnnsgnzls.modern.presentation.ui.views.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Composable
fun GameRating(rating: Double?) {
    val text =
        if (rating == null) "No rating available..."
        else "Rating: ${"%.1f".format(rating)} / 5"

    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewGameRating() {
    ModernAndroidTheme {
        GameRating(4.0)
    }
}
