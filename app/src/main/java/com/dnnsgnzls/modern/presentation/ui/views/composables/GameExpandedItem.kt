package com.dnnsgnzls.modern.presentation.ui.views.composables

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnnsgnzls.modern.presentation.ui.theme.ModernAndroidTheme

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewGameExpandedItem() {
    ModernAndroidTheme {
        GameExpandedItem { _, _ -> } /* no-op for clicks */
    }
}

@Composable
fun GameExpandedItem(
    onSaveReview: (String, String) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        SendReviewForm(onSaveReview)
    }
}

@Composable
private fun SendReviewForm(
    onSaveReview: (String, String) -> Unit
) {
    var titleState by remember { mutableStateOf("") }
    var detailsState by remember { mutableStateOf("") }


    var titleError by remember { mutableStateOf(false) }
    var detailsError by remember { mutableStateOf(false) }

    fun validateInput(): Boolean {
        titleError = titleState.isBlank()
        detailsError = detailsState.isBlank()
        return !titleError && !detailsError
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 4.dp, bottom = 8.dp)
                .weight(1f),
        ) {
            Text(
                text = "Add a Review",
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = titleState,
                onValueChange = { value ->
                    titleState = value
                    titleError = value.isEmpty()
                },
                isError = titleError,
                label = { Text(text = "Short Title", fontSize = 14.sp) },
                placeholder = {
                    Text(
                        text =
                        if (titleError) "Please provide a title"
                        else "This game is awesome!",
                        fontSize = 14.sp
                    )
                },
                shape = MaterialTheme.shapes.medium,
                textStyle = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = detailsState,
                onValueChange = { value ->
                    detailsState = value
                    detailsError = value.isEmpty()
                },
                isError = detailsError,
                label = { Text(text = "Review Details", fontSize = 14.sp) },
                placeholder = {
                    Text(
                        text =
                        if (detailsError) "Please provide a description"
                        else "100/10 Recommended to play!",
                        fontSize = 14.sp
                    )
                },
                shape = MaterialTheme.shapes.medium,
                textStyle = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                maxLines = 3
            )
        }


        Box(
            modifier = Modifier
                .padding(top = 8.dp, start = 4.dp, end = 8.dp, bottom = 8.dp)
                .background(
                    MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                )
        ) {
            IconButton(
                onClick = {
                    if (validateInput()) {
                        onSaveReview(titleState, detailsState)
                        // Reset input fields
                        titleState = ""
                        detailsState = ""
                    }
                },
            ) {
                Icon(
                    Icons.Filled.Check,
                    contentDescription = "Save review",
                    tint = Color.Black
                )
            }
        }
    }
}
