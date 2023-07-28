package com.dnnsgnzls.modern.data.repository

import com.dnnsgnzls.modern.data.db.GameEntity
import com.dnnsgnzls.modern.domain.mapper.mapGameFromDto
import com.dnnsgnzls.modern.domain.mapper.mapGamesFromDto
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.model.Games
import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.framework.network.RawgApi
import com.dnnsgnzls.modern.framework.persistence.GameDao
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GamesRepositoryImpl(
    private val rawgApi: RawgApi,
    private val gameDao: GameDao,
    private val dispatcher: CoroutineDispatcher
) : GamesRepository {
    override fun getGame(id: String): Flow<Response<Game>> = flow {
        try {
            emit(Response.Loading)
            val gameDto = rawgApi.gameDetails(id)
            val game = mapGameFromDto(gameDto)

            emit(Response.Success(game))
        } catch (e: Exception) {
            emit(Response.Error(e))
            e.printStackTrace()
        }
    }.flowOn(dispatcher)

    override fun getGames(searchQuery: String, page: Int): Flow<Response<Games>> = flow {
        try {
            emit(Response.Loading)
            val gamesDto = rawgApi.games(searchQuery, page)
            val games = mapGamesFromDto(gamesDto)

            emit(Response.Success(games))
        } catch (e: Exception) {
            emit(Response.Error(e))
            e.printStackTrace()
        }
    }.flowOn(dispatcher)

    override fun getFavouriteGameIds(): Flow<Response<List<Long>>> = flow {
        try {
            emit(Response.Loading)

            gameDao.getAllIds().collect {
                emit(Response.Success(it))
            }
        } catch (e: Exception) {
            emit(Response.Error(e))
            e.printStackTrace()
        }
    }.flowOn(dispatcher)

    override fun saveGame(game: Game): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val gameEntity: GameEntity = GameEntity.fromDomainModel(game)
            gameDao.insert(gameEntity)

            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e))
            e.printStackTrace()
        }
    }.flowOn(dispatcher)

    override fun saveGames(games: List<Game>): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val gameEntityList: List<GameEntity> = games.map { game ->
                GameEntity.fromDomainModel((game))
            }
            gameDao.insertAll(gameEntityList)

            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e))
            e.printStackTrace()
        }
    }.flowOn(dispatcher)

    override fun deleteGame(game: Game): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            gameDao.delete(GameEntity.fromDomainModel(game))

            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e))
            e.printStackTrace()
        }
    }.flowOn(dispatcher)
}