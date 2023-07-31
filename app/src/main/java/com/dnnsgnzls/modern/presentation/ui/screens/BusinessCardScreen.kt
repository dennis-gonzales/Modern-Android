package com.dnnsgnzls.modern.presentation.ui.screens

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnnsgnzls.modern.R
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Preview(showBackground = true)
@Composable
fun PreviewBusinessCardScreen() {
    ModernAndroidTheme {
        BusinessCardScreen()
    }
}

@Composable
fun CardProfile(modifier: Modifier = Modifier) {
    val profileImg = painterResource(R.drawable.profile_img)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = profileImg,
            contentDescription = null,
            modifier.padding(8.dp)
        )
        Text(
            text = "Dennis Gonzales",
            fontSize = 20.sp,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(8.dp),
        )
        Text(
            text = "Senior Software Engineer",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(8.dp)
        )
    }
}

@Composable
fun CardContent(content: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val linkIcon = painterResource(R.drawable.ic_link)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(content))
                    context.startActivity(intent)
                }
                .padding(4.dp)
        ) {
            Icon(
                painter = linkIcon,
                contentDescription = null,
                modifier = modifier
                    .weight(0.2F, true)
            )

            Text(
                text = content,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = modifier
                    .weight(1F, true)
            )
        }

        Spacer(modifier = Modifier.size(10.dp))
    }
}


@Composable
fun BusinessCardScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.profile_img),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            alpha = 0.25f,
            modifier = Modifier.fillMaxSize(),
        )


        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                CardProfile()
            }

            Column {
                CardContent("https://github.com/dennis-gonzales")
                CardContent("https://www.linkedin.com/in/dennis-gonzales/")
                CardContent("https://twitter.com/dnnsgnzls")
            }
        }
    }
}