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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.model.Review
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.framework.utils.SnackbarMessage
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
    val reviewsState: Response<List<Review>> by gamesViewModel.gameReviews.collectAsStateWithLifecycle()
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

    val favGameIds by remember {
        derivedStateOf {
            (favouriteGameIdsState as? Response.Success)?.data ?: emptyList()
        }
    }

    LaunchedEffect(Unit) {
        gamesViewModel.getFavouriteGameIds()
    }

    LaunchedEffect(gameId) {
        gamesViewModel.fetchSingleGame(gameId)
    }

    LaunchedEffect(gameId) {
        gamesViewModel.getReviewsByGameId(gameId.toLong())
    }

    LaunchedEffect(gamesViewModel.snackBarMessages) {
        gamesViewModel.snackBarMessages.collect { message ->
            when (message) {
                is SnackbarMessage.Success -> {
                    snackBarHostState.showSnackbar(
                        message.message,
                        withDismissAction = true
                    )
                }

                is SnackbarMessage.Error -> {
                    snackBarHostState.showSnackbar(
                        message.message,
                        withDismissAction = true,
                        duration = SnackbarDuration.Indefinite
                    )
                }
            }
        }
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
        val isFavourite = favGameIds.contains((gameState as? Response.Success)?.data?.id)

        GameDetailsView(
            gameState = gameState,
            reviewsState = reviewsState,
            isFavourite = isFavourite,
            onBack = { navController.popBackStack() },
            onToggleFavourite = { game ->
                composableScope.launch {
                    gamesViewModel.saveOrDeleteGameWithMessage(game, isFavourite)
                }
            },
            onDeleteReview = { review ->
                composableScope.launch {
                    gamesViewModel.deleteGameReview(review)
                }
            }
        )
    }
}
