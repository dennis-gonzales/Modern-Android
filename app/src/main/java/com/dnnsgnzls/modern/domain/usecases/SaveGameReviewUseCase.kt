package com.dnnsgnzls.modern.domain.usecases

import com.dnnsgnzls.modern.domain.model.Review
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface SaveGameReviewUseCase {
    operator fun invoke(review: Review): Flow<Response<Boolean>>
}