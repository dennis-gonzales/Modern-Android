package com.dnnsgnzls.modern.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.domain.usecases.GamesUseCases
import com.dnnsgnzls.modern.domain.usecases.GetGamesUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gamesUseCases: GamesUseCases
) : ViewModel() {
    private val _games = MutableStateFlow<Response<Games>>(Response.Loading)
    private val _queryText = MutableStateFlow("")

    val games: StateFlow<Response<Games>>
        get() = _games
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
//            when (response) {
//                is Response.Success -> {
//                    val prevGames: GameMock? = when (_games.value) {
//                        is Response.Success -> {
//                            (_games.value as Response.Success<GameMock>).data
//                        }
//                        else -> null
//                    }
//                    val newGames = response.data
//                    val updatedGames = prevGames?.updateWith(newGames) ?: newGames
//
//                    _games.value = Response.Success(updatedGames)
//                    currentPage++
//                }
//                else -> _games.value = response
//            }
        }
    }

    fun inputQueryChanged(input: String) {
        _queryText.value = input
        queryInput.trySend(input)
    }
}
