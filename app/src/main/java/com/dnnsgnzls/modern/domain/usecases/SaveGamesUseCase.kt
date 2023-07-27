package com.dnnsgnzls.modern.domain.usecases

import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface SaveGamesUseCase {
    operator fun invoke(games: List<Game>): Flow<Response<Boolean>>
}