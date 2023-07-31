package com.dnnsgnzls.modern.domain.usecases

import com.dnnsgnzls.modern.domain.model.Review
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetGameReviewsByGameIdUseCase {
    operator fun invoke(gameId: Long): Flow<Response<List<Review>>>
}