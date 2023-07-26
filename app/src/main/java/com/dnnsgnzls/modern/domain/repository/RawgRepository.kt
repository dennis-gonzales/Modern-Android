package com.dnnsgnzls.modern.domain.repository

import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface RawgRepository {
    fun getGames(searchQuery: String): Flow<Response<Games>>
}
