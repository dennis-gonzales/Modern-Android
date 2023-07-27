package com.dnnsgnzls.modern.presentation.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
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
        GamesSearchBar(
            queryTextState = queryTextState,
            onQueryTextChanged = gamesViewModel::inputQueryChanged
        )

        GamesContent(gamesState = gamesState)
    }
}
