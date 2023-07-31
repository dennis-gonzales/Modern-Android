package com.dnnsgnzls.modern.presentation.ui.views.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dnnsgnzls.modern.R
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Preview
@Composable
fun PreviewGameButton() {
    ModernAndroidTheme {
        GameButton("Favourite", painterResource(R.drawable.ic_favourite)) {} /* no-op for click */
    }
}

@Composable
fun GameButton(
    text: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                text = text,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}