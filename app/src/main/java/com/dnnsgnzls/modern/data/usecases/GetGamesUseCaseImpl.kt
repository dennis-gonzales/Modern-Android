package com.dnnsgnzls.modern.data.usecases

import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.domain.usecases.GetGamesUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

class GetGamesUseCaseImpl(
    private val gamesRepository: GamesRepository
) : GetGamesUseCase {
    override operator fun invoke(searchQuery: String, page: Int): Flow<Response<List<Game>>> {
        return gamesRepository.getGames(searchQuery, page)
    }
}