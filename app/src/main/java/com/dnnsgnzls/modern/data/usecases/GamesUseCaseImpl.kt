package com.dnnsgnzls.modern.data.usecases

import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.domain.repository.RawgRepository
import com.dnnsgnzls.modern.domain.usecases.GetGamesUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

class GamesUseCaseImpl(
    private val rawgRepository: RawgRepository
) : GetGamesUseCase {
    override operator fun invoke(searchQuery: String, page: Int): Flow<Response<Games>> {
        return rawgRepository.getGames(searchQuery, page)
    }
}