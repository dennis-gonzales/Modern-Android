package com.dnnsgnzls.modern.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.domain.repository.RawgRepository
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
    private val repository: RawgRepository
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
            fetchGamesForQuery("")

            queryInput.receiveAsFlow()
//                .filter { }
                .debounce(500)
                .collect { searchQuery ->
                    fetchGamesForQuery(searchQuery)
                }
        }
    }

    private suspend fun fetchGamesForQuery(searchQuery: String) {
        repository.getGames(searchQuery).collect { response ->
            _games.value = response
        }
    }

    fun inputQueryChanged(input: String) {
        _queryText.value = input
        queryInput.trySend(input)
    }
}
