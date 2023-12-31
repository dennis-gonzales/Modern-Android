package com.dnnsgnzls.modern.data.repository

import com.dnnsgnzls.modern.data.db.GameEntity
import com.dnnsgnzls.modern.data.db.ReviewEntity
import com.dnnsgnzls.modern.domain.mapper.mapGameFromDto
import com.dnnsgnzls.modern.domain.mapper.mapGamesFromDto
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.model.Review
import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.framework.network.RawgApi
import com.dnnsgnzls.modern.framework.persistence.GameDao
import com.dnnsgnzls.modern.framework.persistence.ReviewDao
import com.dnnsgnzls.modern.framework.utils.Response
import com.dnnsgnzls.modern.framework.utils.catchException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GamesRepositoryImpl(
    private val rawgApi: RawgApi,
    private val gameDao: GameDao,
    private val reviewDao: ReviewDao,
    private val dispatcher: CoroutineDispatcher
) : GamesRepository {
    override fun getGame(id: String, tryQueryingFromCache: Boolean): Flow<Response<Game>> = flow {
        emit(Response.Loading)

        suspend fun getGameFromApi() = mapGameFromDto(rawgApi.gameDetails(id))

        if (tryQueryingFromCache) {
            gameDao.get(id.toLong()).collect {
                if (it != null) emit(Response.Success(it.toDomainModel()))
                else emit(Response.Success(getGameFromApi()))
            }
        } else {
            emit(Response.Success(getGameFromApi()))
        }
    }.catchException().flowOn(dispatcher)

    override fun getGames(searchQuery: String, page: Int): Flow<Response<List<Game>>> = flow {
        emit(Response.Loading)
        val gamesDto = rawgApi.games(searchQuery, page)
        val games = mapGamesFromDto(gamesDto)

        emit(Response.Success(games.results))
    }.catchException().flowOn(dispatcher)

    override fun getFavouriteGames(): Flow<Response<List<Game>>> = flow {
        emit(Response.Loading)

        gameDao.getAll().collect { gameEntityList ->
            val games = gameEntityList.map { it.toDomainModel() }
            emit(Response.Success(games))
        }
    }.catchException().flowOn(dispatcher)

    override fun getFavouriteGameIds(): Flow<Response<List<Long>>> = flow {
        emit(Response.Loading)

        gameDao.getAllIds().collect {
            emit(Response.Success(it))
        }
    }.catchException().flowOn(dispatcher)

    override fun getGameReviews(gameId: Long): Flow<Response<List<Review>>> = flow {
        emit(Response.Loading)

        reviewDao.getAllByGameId(gameId).collect { reviewListEntity ->
            val reviewList = reviewListEntity.map { it.toDomainModel() }
            emit(Response.Success(reviewList))
        }
    }.catchException().flowOn(dispatcher)

    override fun saveGame(game: Game): Flow<Response<Boolean>> = flow {
        emit(Response.Loading)
        val gameEntity: GameEntity = GameEntity.fromDomainModel(game)
        gameDao.insert(gameEntity)

        emit(Response.Success(true))
    }.catchException().flowOn(dispatcher)

    override fun saveGameReview(review: Review): Flow<Response<Boolean>> = flow {
        emit(Response.Loading)
        reviewDao.insert(ReviewEntity.fromDomainModel(review))

        emit(Response.Success(true))
    }.catchException().flowOn(dispatcher)

    override fun deleteGame(game: Game): Flow<Response<Boolean>> = flow {
        emit(Response.Loading)
        gameDao.delete(GameEntity.fromDomainModel(game))

        emit(Response.Success(true))
    }.catchException().flowOn(dispatcher)

    override fun deleteReview(review: Review): Flow<Response<Boolean>> = flow {
        emit(Response.Loading)
        reviewDao.delete(ReviewEntity.fromDomainModel(review))

        emit(Response.Success(true))
    }.catchException().flowOn(dispatcher)

    override fun deleteAllReviewsByGameIs(gameId: Long): Flow<Response<Boolean>> = flow {
        emit(Response.Loading)
        reviewDao.deleteAllByGameId(gameId)

        emit(Response.Success(true))
    }.catchException().flowOn(dispatcher)
}