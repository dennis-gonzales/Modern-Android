package com.dnnsgnzls.modern.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.presentation.ui.views.GameDetailsView
import com.dnnsgnzls.modern.presentation.viewmodels.GamesViewModel
import kotlinx.coroutines.launch

@Composable
fun GameDetailsScreen(
    gameId: String?,
    gamesViewModel: GamesViewModel,
    navController: NavController,
    snackBarHostState: SnackbarHostState,
    paddingValues: PaddingValues
) {
    val favouriteGameIdsState: Response<List<Long>> by gamesViewModel.favouriteGameIds.collectAsStateWithLifecycle()
    val gameState: Response<Game> by gamesViewModel.game.collectAsStateWithLifecycle()
    val composableScope = rememberCoroutineScope()

    if (gameId == null) {
        Text(
            text = "There was problem loading that game, please try again later.",
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
        return
    }

    LaunchedEffect(Unit) {
        gamesViewModel.getFavouriteGameIds()
    }

    LaunchedEffect(gameId) {
        gamesViewModel.fetchSingleGame(gameId)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            ),
    ) {
        val favGameIds = (favouriteGameIdsState as? Response.Success)?.data ?: emptyList()
        val isFavourite = favGameIds.contains((gameState as? Response.Success)?.data?.id)

        GameDetailsView(
            gameState = gameState,
            isFavourite = isFavourite,
            onBack = { navController.popBackStack() },
            onToggleFavourite = { game ->
                composableScope.launch {
                    gamesViewModel.saveOrDeleteGame(game, isFavourite).collect {
                        when (it) {
                            is Response.Success -> {
                                val message =
                                    if (isFavourite) "${game.name} is no longer your favourite."
                                    else "${game.name} is now your favourite."

                                snackBarHostState.showSnackbar(
                                    message,
                                    withDismissAction = true
                                )
                            }

                            is Response.Error -> {
                                snackBarHostState.showSnackbar(
                                    it.exception.message ?: "Unknown error",
                                    withDismissAction = true,
                                    duration = SnackbarDuration.Indefinite
                                )
                            }

                            is Response.Loading -> {} // ignore
                        }
                    }
                }
            }
        )
    }
}
