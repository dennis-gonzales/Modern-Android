package com.dnnsgnzls.modern.data.usecases

import com.dnnsgnzls.modern.domain.model.Review
import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.domain.usecases.DeleteGameReviewUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

class DeleteGameReviewUseCaseImpl(
    private val gamesRepository: GamesRepository
) : DeleteGameReviewUseCase {
    override operator fun invoke(review: Review): Flow<Response<Boolean>> {
        return gamesRepository.deleteReview(review)
    }
}