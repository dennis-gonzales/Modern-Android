package com.dnnsgnzls.modern.domain.usecases

import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface DeleteGameUseCase {
    operator fun invoke(game: Game): Flow<Response<Boolean>>
}