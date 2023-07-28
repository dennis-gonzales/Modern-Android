package com.dnnsgnzls.modern.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.domain.usecases.GamesUseCases
import com.dnnsgnzls.modern.framework.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gamesUseCases: GamesUseCases
) : ViewModel() {
    private val _games = MutableStateFlow<Response<Games>>(Response.Loading)
    private val _game = MutableStateFlow<Response<Game>>(Response.Loading)
    private val _favouriteGameIds = MutableStateFlow<Response<List<Long>>>(Response.Loading)
    private val _queryText = MutableStateFlow("")

    val games: StateFlow<Response<Games>>
        get() = _games
    val game: StateFlow<Response<Game>>
        get() = _game
    val favouriteGameIds: StateFlow<Response<List<Long>>>
        get() = _favouriteGameIds
    val queryText: StateFlow<String>
        get() = _queryText

    private val queryInput = Channel<String>(Channel.CONFLATED)

    init {
        fetchGames()
    }

    @OptIn(FlowPreview::class)
    private fun fetchGames() {
        viewModelScope.launch {
            fetchGamesForQuery("", 1)

            queryInput.receiveAsFlow()
                .debounce(500)
                .collect { searchQuery ->
                    fetchGamesForQuery(searchQuery, 1)
                }
        }
    }

    private suspend fun fetchGamesForQuery(searchQuery: String, page: Int) {
        gamesUseCases.getGamesUseCase(searchQuery, page).collect { response ->
            _games.value = response
        }
    }

    private suspend fun fetchGame(gameId: Long) {
        gamesUseCases.getGameUseCase(gameId).collect { response ->
            _game.value = response
        }
    }

    fun saveFavouriteGame(game: Game) = gamesUseCases.saveGameUseCase(game)

    fun deleteFavouriteGame(game: Game) = gamesUseCases.deleteGameUseCase(game)

    fun saveOrDeleteGame(game: Game, isFavourite: Boolean): Flow<Response<Boolean>> = flow {
        if (isFavourite) deleteFavouriteGame(game).collect { emit(it) }
        else saveFavouriteGame(game).collect { emit(it) }
    }

    suspend fun getFavouriteGameIds() {
        gamesUseCases.getFavouriteGameIdsUseCase().collect { response ->
            _favouriteGameIds.value = response
        }
    }

    fun fetchSingleGame(gameId: Long) {
        viewModelScope.launch {
            fetchGame(gameId)
        }
    }

    fun saveFavouriteGames(games: List<Game>) {
        viewModelScope.launch {
            gamesUseCases.saveGamesUseCase(games)
        }
    }

    fun inputQueryChanged(input: String) {
        _queryText.value = input
        queryInput.trySend(input)
    }
}
