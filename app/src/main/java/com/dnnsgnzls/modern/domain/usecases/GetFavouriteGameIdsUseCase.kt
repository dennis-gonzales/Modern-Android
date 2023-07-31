package com.dnnsgnzls.modern.domain.usecases

import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetFavouriteGameIdsUseCase {
    operator fun invoke(): Flow<Response<List<Long>>>
}