package com.dnnsgnzls.modern.data.usecases

import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.domain.repository.RawgRepository
import com.dnnsgnzls.modern.domain.usecases.GetGamesUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

class GetGamesUseCaseImpl(
    private val rawgRepository: RawgRepository
) : GetGamesUseCase {
    override operator fun invoke(): Flow<Response<List<Games>>> {
        return rawgRepository.getGames()
    }
}
