package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.presentation.viewmodels.GamesViewModel
import kotlinx.coroutines.launch

@Composable
fun GameListScreen(
    gamesViewModel: GamesViewModel,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues
) {
    val gamesState: Response<Games> by gamesViewModel.games.collectAsStateWithLifecycle()
    val queryTextState: String by gamesViewModel.queryText.collectAsStateWithLifecycle()
    val composableScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GamesSearchBar(
            queryTextState = queryTextState,
            onQueryTextChanged = gamesViewModel::inputQueryChanged
        )

        GamesContent(
            gamesState = gamesState,
            onItemClick = { id -> gamesViewModel.fetchSingleGame(id) },
            onSaveGame = { game ->
                composableScope.launch {
                    gamesViewModel.saveFavouriteGame(game).collect {
                        when (it) {
                            is Response.Success -> {
                                snackbarHostState.showSnackbar(
                                    "Game saved successfully.",
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
