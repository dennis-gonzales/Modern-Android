package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme


@Preview(showBackground = true)
@Composable
fun PreviewGamesSearchBar() {
    ModernAndroidTheme {
        GamesSearchBar(
            queryTextState = "",
            onQueryTextChanged = { /* No-op for preview */ }
        )
    }
}

@Composable
fun GamesSearchBar(
    queryTextState: String,
    onQueryTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = queryTextState,
        onValueChange = onQueryTextChanged,
        label = { Text(text = "Search for games") },
        placeholder = { Text(text = "Dota 2") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    )
}
