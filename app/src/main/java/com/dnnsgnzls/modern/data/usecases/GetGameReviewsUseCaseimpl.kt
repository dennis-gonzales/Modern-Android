package com.dnnsgnzls.modern.data.usecases

import com.dnnsgnzls.modern.domain.model.Review
import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.domain.usecases.GetGameReviewsUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

class GetGameReviewsUseCaseimpl(
    private val gamesRepository: GamesRepository
): GetGameReviewsUseCase {
    override operator fun invoke(gameId: Long): Flow<Response<List<Review>>> {
        return gamesRepository.getGameReviews(gameId)
    }
}