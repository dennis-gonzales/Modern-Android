package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.presentation.ui.nav.Screen
import com.dnnsgnzls.modern.presentation.viewmodels.GamesViewModel
import kotlinx.coroutines.launch

@Composable
fun GameListScreen(
    gamesViewModel: GamesViewModel,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues
) {
    val favouriteGameIdsState: Response<List<Long>> by gamesViewModel.favouriteGameIds.collectAsStateWithLifecycle()
    val gamesState: Response<Games> by gamesViewModel.games.collectAsStateWithLifecycle()
    val queryTextState: String by gamesViewModel.queryText.collectAsStateWithLifecycle()
    val composableScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        gamesViewModel.getFavouriteGameIds()
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
        GamesSearchBar(
            queryTextState = queryTextState,
            onQueryTextChanged = gamesViewModel::inputQueryChanged
        )

        val favGameIds = (favouriteGameIdsState as? Response.Success)?.data ?: emptyList()

        GamesContent(
            gamesState = gamesState,
            favouriteGameIds = favGameIds,
            onItemClick = { game ->
                navController.navigate(Screen.GameDetails.createRoute(game.id))
            },
            onToggleFavourite = { game ->
                composableScope.launch {
                    val isFavourite = favGameIds.contains(game.id)

                    gamesViewModel.saveOrDeleteGame(game, isFavourite).collect {
                        when (it) {
                            is Response.Success -> {
                                val message =
                                    if (isFavourite) "${game.name} is no longer your favourite."
                                    else "${game.name} is now your favourite."

                                snackbarHostState.showSnackbar(
                                    message,
                                    withDismissAction = true
                                )
                            }

                            is Response.Error -> {
                                snackbarHostState.showSnackbar(
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
