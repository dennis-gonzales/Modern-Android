package com.dnnsgnzls.modern.presentation.ui.views.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Composable
fun GameReleaseDate(releaseDate: String) {
    Text(
        text = "Released: $releaseDate",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewGameReleaseDate() {
    ModernAndroidTheme {
        GameReleaseDate("2013-07-09")
    }
}