package com.dnnsgnzls.modern.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.model.Review
import com.dnnsgnzls.modern.domain.usecases.GamesUseCases
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.framework.utils.ResponseSnackBarHandler
import com.dnnsgnzls.modern.framework.utils.SnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
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
    private val _games = MutableStateFlow<Response<List<Game>>>(Response.Loading)
    private val _game = MutableStateFlow<Response<Game>>(Response.Loading)
    private val _favouriteGames = MutableStateFlow<Response<List<Game>>>(Response.Loading)
    private val _favouriteGameIds = MutableStateFlow<Response<List<Long>>>(Response.Loading)
    private val _gameReviews = MutableStateFlow<Response<List<Review>>>(Response.Loading)
    private val _queryText = MutableStateFlow("")
    private val _snackBarMessages = MutableSharedFlow<SnackbarMessage>()
    private val responseSnackBarHandler = ResponseSnackBarHandler(_snackBarMessages)
    private val queryTextChannel = Channel<String>(Channel.CONFLATED)

    val games: StateFlow<Response<List<Game>>>
        get() = _games
    val game: StateFlow<Response<Game>>
        get() = _game
    val favouriteGames: StateFlow<Response<List<Game>>>
        get() = _favouriteGames
    val favouriteGameIds: StateFlow<Response<List<Long>>>
        get() = _favouriteGameIds
    val gameReviews: StateFlow<Response<List<Review>>>
        get() = _gameReviews
    val queryText: StateFlow<String>
        get() = _queryText
    val snackBarMessages: SharedFlow<SnackbarMessage>
        get() = _snackBarMessages

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

    private fun saveOrDeleteGame(game: Game, isFavourite: Boolean): Flow<Response<Boolean>> = flow {
        if (isFavourite) {
            gamesUseCases.deleteGameUseCase(game).collect { emit(it) }
            deleteAllReviewsByGameId(game) // when deleting a game aii the reviews are alo deleted
        } else gamesUseCases.saveGameUseCase(game).collect { emit(it) }
    }

    suspend fun saveOrDeleteGameWithMessage(game: Game, isFavourite: Boolean) {
        saveOrDeleteGame(game, isFavourite).collect { response ->
            val message =
                if (isFavourite) "\"${game.name}\" is no longer your favourite."
                else "\"${game.name}\" is now your favourite."
            responseSnackBarHandler.handleResponse(response, message)
        }
    }

    suspend fun getFavouriteGames() {
        gamesUseCases.getFavouriteGamesUseCase().collect { response ->
            _favouriteGames.value = response
        }
    }

    suspend fun getFavouriteGameIds() {
        gamesUseCases.getFavouriteGameIdsUseCase().collect { response ->
            _favouriteGameIds.value = response
        }
    }

    suspend fun saveGameReview(review: Review) {
        gamesUseCases.saveGameReviewUseCase(review).collect { response ->
            val message = "\"${review.title}\" saved successfully."
            responseSnackBarHandler.handleResponse(response, message)
        }
    }

    suspend fun deleteGameReview(review: Review) {
        gamesUseCases.deleteGameReviewUseCase(review).collect { response ->
            val message = "\"${review.title}\" deleted successfully."
            responseSnackBarHandler.handleResponse(response, message)
        }
    }

    suspend fun getReviewsByGameId(gameId: Long) {
        gamesUseCases.getGameReviewsByGameIdUseCase(gameId).collect { reviewList ->
            _gameReviews.value = reviewList
        }
    }

    private suspend fun deleteAllReviewsByGameId(game: Game) {
        gamesUseCases.deleteGamesReviewsByGameIdUseCase(game.id).collect { response ->
            val message = "\"All reviews of ${game.name}\" deleted successfully."
            responseSnackBarHandler.handleResponse(response, message)
        }
    }

    fun inputQueryChanged(input: String) {
        _queryText.value = input
        queryTextChannel.trySend(input)
    }
}
