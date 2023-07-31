package com.dnnsgnzls.modern.domain.usecases

import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetFavouriteGamesUseCase {
    operator fun invoke(): Flow<Response<List<Game>>>
}