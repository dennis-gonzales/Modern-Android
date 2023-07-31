package com.dnnsgnzls.modern.domain.usecases

import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface DeleteGameReviewsByGameIdUseCase {
    operator fun invoke(gameId: Long): Flow<Response<Boolean>>
}