package com.dnnsgnzls.modern.data.usecases

import com.dnnsgnzls.modern.domain.model.Review
import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.domain.usecases.GetGameReviewsByGameIdUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

class GetGameReviewsByGameIdUseCaseimpl(
    private val gamesRepository: GamesRepository
): GetGameReviewsByGameIdUseCase {
    override operator fun invoke(gameId: Long): Flow<Response<List<Review>>> {
        return gamesRepository.getGameReviews(gameId)
    }
}