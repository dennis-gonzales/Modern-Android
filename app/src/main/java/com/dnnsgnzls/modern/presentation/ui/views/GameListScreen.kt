package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.presentation.viewmodels.GamesViewModel

@Composable
fun GameListScreen(
    navController: NavHostController,
    gamesViewModel: GamesViewModel,
    paddingValues: PaddingValues
) {
    val gamesState: Response<Games> by gamesViewModel.games.collectAsStateWithLifecycle()
    val queryTextState: String by gamesViewModel.queryText.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = queryTextState,
            onValueChange = gamesViewModel::inputQueryChanged,
            label = { Text(text = "Search for games") },
            placeholder = { Text(text = "Games") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            /**
             * When a property is used with `by` delegate-
             * Kotlin cannot guarantee the property hasn't changed-
             * between the `is` check and the usage, thus it won't do the smart cast.
             */
            when (val currentGamesState: Response<Games> =
                gamesState) { // creating an immutable  copy of the property
                is Response.Success -> {
                    ShowCharactersList(currentGamesState.data, navController)
                }

                is Response.Loading -> {
                    CircularProgressIndicator()
                }

                is Response.Error -> {
                    Text(text = "Error: ${currentGamesState.exception.message}")
                }
            }
        }

    }
}

@Composable
fun ShowCharactersList(
    response: Games,
    navController: NavHostController
) {
    response.let { games ->
        LazyColumn(
            modifier = Modifier.background(Color.LightGray),
            verticalArrangement = Arrangement.Top
        ) {
            items(games.results) { result ->
                val imageUrl = result.backgroundImage
                val title = result.name
                val description = result.released
                val context = LocalContext.current
                val id = result.id

                Column(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.White)
                        .padding(4.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
//                            .clickable {
//                                if (result.id != null)
//                                    navController.navigate(Destination.CharacterDetail.createRoute(id))
//                                else
//                                    Toast
//                                        .makeText(context, "Character id is null", Toast.LENGTH_SHORT)
//                                        .show()
//                            }
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(4.dp)
                                .width(100.dp)
                        )

                        Column(modifier = Modifier.padding(4.dp)) {
                            Text(text = title ?: "Unnamed", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        }
                    }

                    Text(text = description ?: "Unknown", maxLines = 4, fontSize = 14.sp)
                }
            }
        }
    }
}