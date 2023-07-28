package com.dnnsgnzls.modern.domain.usecases

import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetGameUseCase {
    operator fun invoke(id: String, tryQueryingFromCache: Boolean): Flow<Response<Game>>
}