package com.dnnsgnzls.modern.presentation.ui.views.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dnnsgnzls.modern.domain.mock.Dota2
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Preview(showBackground = true)
@Composable
fun PreviewGameReleaseDate() {
    ModernAndroidTheme {
        GameReleaseDate(Dota2.released.toString())
    }
}

@Composable
fun GameReleaseDate(releaseDate: String) {
    Text(
        text = "Released: $releaseDate",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
}
