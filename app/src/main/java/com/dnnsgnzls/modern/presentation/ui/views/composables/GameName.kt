package com.dnnsgnzls.modern.presentation.ui.views.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Composable
fun GameName(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewGameName() {
    ModernAndroidTheme {
        GameName("Game Name")
    }
}