package com.dnnsgnzls.modern.data.usecases

import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.domain.usecases.DeleteGameReviewsByGameIdUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

class DeleteGameReviewsByGameIdUseCaseImpl(
    private val gamesRepository: GamesRepository
) : DeleteGameReviewsByGameIdUseCase {
    override operator fun invoke(gameId: Long): Flow<Response<Boolean>> {
        return gamesRepository.deleteAllReviewsByGameIs(gameId)
    }
}