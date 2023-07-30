package com.dnnsgnzls.modern.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.domain.usecases.GamesUseCases
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.framework.utils.SnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    private val queryTextChannel = Channel<String>(Channel.CONFLATED)

    val games: StateFlow<Response<Games>>
        get() = _games
    val game: StateFlow<Response<Game>>
        get() = _game
    val favouriteGameIds: StateFlow<Response<List<Long>>>
        get() = _favouriteGameIds
    val queryText: StateFlow<String>
        get() = _queryText

    val snackBarMessages = MutableSharedFlow<SnackbarMessage>()


    init {
        fetchGames()
    }

    @OptIn(FlowPreview::class)
    private fun fetchGames() {
        viewModelScope.launch {
            fetchGamesForQuery("", 1)

            queryTextChannel.receiveAsFlow()
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

    suspend fun fetchSingleGame(gameId: String) {
        gamesUseCases.getGameUseCase(gameId, tryQueryingFromCache = true).collect { response ->
            _game.value = response
        }
    }

    suspend fun saveOrDeleteGameWithMessage(game: Game, isFavourite: Boolean) {
        saveOrDeleteGame(game, isFavourite).collect { response ->
            when (response) {
                is Response.Success -> {
                    val message = if (isFavourite) "${game.name} is no longer your favourite."
                    else "${game.name} is now your favourite."

                    snackBarMessages.emit(SnackbarMessage.Success(message))
                }

                is Response.Error -> {
                    snackBarMessages.emit(SnackbarMessage.Error(response.exception.message ?: "Unknown error"))
                }

                is Response.Loading -> {} // ignore
            }
        }
    }

    private fun saveOrDeleteGame(game: Game, isFavourite: Boolean): Flow<Response<Boolean>> = flow {
        if (isFavourite) gamesUseCases.deleteGameUseCase(game).collect { emit(it) }
        else gamesUseCases.saveGameUseCase(game).collect { emit(it) }
    }

    suspend fun getFavouriteGameIds() {
        gamesUseCases.getFavouriteGameIdsUseCase().collect { response ->
            _favouriteGameIds.value = response
        }
    }

    fun inputQueryChanged(input: String) {
        _queryText.value = input
        queryTextChannel.trySend(input)
    }
}
