package com.dnnsgnzls.modern.data.repository

import com.dnnsgnzls.modern.domain.mapper.mapGamesFromDto
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.domain.repository.RawgRepository
import com.dnnsgnzls.modern.framework.network.RawgApi
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RawgRepositoryImpl(
    private val rawgApi: RawgApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RawgRepository {
    override fun getGames(): Flow<Response<List<Games>>> = flow {
        try {
            emit(Response.Loading)
            val gamesDto = rawgApi.games()
            val games = gamesDto.map { mapGamesFromDto(it) }

            emit(Response.Success(games))
        } catch (e: Exception) {
            emit(Response.Error(e))
            e.printStackTrace()
        }
    }.flowOn(dispatcher)
}