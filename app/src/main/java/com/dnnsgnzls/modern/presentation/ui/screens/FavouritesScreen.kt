package com.dnnsgnzls.modern.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.model.Review
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.framework.utils.SnackbarMessage
import com.dnnsgnzls.modern.presentation.ui.nav.Screen
import com.dnnsgnzls.modern.presentation.ui.views.GamesView
import com.dnnsgnzls.modern.presentation.viewmodels.GamesViewModel
import kotlinx.coroutines.launch

@Composable
fun FavouritesScreen(
    gamesViewModel: GamesViewModel,
    navController: NavController,
    snackBarHostState: SnackbarHostState,
    paddingValues: PaddingValues
) {
    val favouriteGamesState: Response<List<Game>> by gamesViewModel.favouriteGames.collectAsStateWithLifecycle()
    val composableScope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        gamesViewModel.getFavouriteGames()
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

    val favGameIds by remember {
        derivedStateOf {
            (favouriteGamesState as? Response.Success)?.data?.map { it.id } ?: emptyList()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GamesView(
            gamesState = favouriteGamesState,
            favouriteGameIds = favGameIds,
            onItemClick = { game ->
                composableScope.launch {
                    gamesViewModel.getReviewsByGameId(game.id)
                }
                navController.navigate(Screen.GameDetails.createRoute(game.id))
            },
            onToggleFavourite = { game ->
                composableScope.launch {
                    val isFavourite = favGameIds.contains(game.id)
                    gamesViewModel.saveOrDeleteGameWithMessage(game, isFavourite)
                }
            },
            expandable = true,
            onSaveReview = { game, title, details ->
                composableScope.launch {
                    gamesViewModel.saveGameReview(Review(game.id, title, details))
                }
            }
        )
    }
}
