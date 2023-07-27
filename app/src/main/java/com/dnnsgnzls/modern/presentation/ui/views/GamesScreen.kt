package com.dnnsgnzls.modern.presentation.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.framework.utils.succeeded
import com.dnnsgnzls.modern.presentation.viewmodels.GamesViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun GameListScreen(
    navController: NavController,
    gamesViewModel: GamesViewModel,
    paddingValues: PaddingValues
) {
    val gamesState: Response<Games> by gamesViewModel.games.collectAsStateWithLifecycle()
    val queryTextState: String by gamesViewModel.queryText.collectAsStateWithLifecycle()
    val composableScope = rememberCoroutineScope()
    val context = LocalContext.current

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
                                Toast.makeText(context, "Save success", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            is Response.Error -> {
                                Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT)
                                    .show()
                            }

                            is Response.Loading -> {} // ignore
                        }
                    }
                }
            }
        )
    }
}
