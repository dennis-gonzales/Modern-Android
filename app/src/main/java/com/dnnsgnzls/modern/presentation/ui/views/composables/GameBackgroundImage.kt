package com.dnnsgnzls.modern.presentation.ui.views.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dnnsgnzls.modern.R
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Composable
fun GameBackgroundImage(url: String, placeholder: Painter? = null) {
    val image: Painter = rememberAsyncImagePainter(
        model = url,
        placeholder = placeholder
    )

    Image(
        painter = image,
        contentDescription = "Game background image",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewGameBackgroundImage() {
    ModernAndroidTheme {
        GameBackgroundImage("N/A", painterResource(R.drawable.ic_game))
    }
}