package com.dnnsgnzls.modern.data.usecases

import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.domain.usecases.GetGameUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

class GetGameUseCaseImpl(
    private val gamesRepository: GamesRepository
): GetGameUseCase {
    override fun invoke(id: String, tryQueryingFromCache: Boolean): Flow<Response<Game>> {
        return gamesRepository.getGame(id, tryQueryingFromCache)
    }

}