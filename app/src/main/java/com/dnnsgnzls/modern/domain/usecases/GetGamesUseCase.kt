package com.dnnsgnzls.modern.domain.usecases

import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetGamesUseCase {
    operator fun invoke(searchQuery: String, page: Int): Flow<Response<List<Game>>>
}
