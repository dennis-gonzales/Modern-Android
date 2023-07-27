package com.dnnsgnzls.modern.domain.repository

import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    fun getGames(searchQuery: String, page: Int): Flow<Response<Games>>
    fun saveGame(game: Game): Flow<Response<Boolean>>
    fun saveGames(games: List<Game>): Flow<Response<Boolean>>
}