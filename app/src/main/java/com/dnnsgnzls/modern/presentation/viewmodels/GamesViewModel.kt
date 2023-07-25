package com.dnnsgnzls.modern.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.domain.usecases.GetGamesUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GamesViewModel(private val getGamesUseCase: GetGamesUseCase) : ViewModel() {
    private val _games = MutableStateFlow<Response<List<Games>>>(Response.Loading)
    val games: StateFlow<Response<List<Games>>>
        = _games

    init {
        fetchGames()
    }

    private fun fetchGames() {
        viewModelScope.launch {
            getGamesUseCase().collect { response ->
                _games.value = response
            }
        }
    }
}
