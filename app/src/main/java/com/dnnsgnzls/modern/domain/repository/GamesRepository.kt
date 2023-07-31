package com.dnnsgnzls.modern.domain.repository

import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.model.Review
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    fun getGame(id: String, tryQueryingFromCache: Boolean): Flow<Response<Game>>
    fun getGames(searchQuery: String, page: Int): Flow<Response<List<Game>>>
    fun getFavouriteGames(): Flow<Response<List<Game>>>
    fun getFavouriteGameIds(): Flow<Response<List<Long>>>
    fun getGameReviews(gameId: Long): Flow<Response<List<Review>>>
    fun saveGame(game: Game): Flow<Response<Boolean>>
    fun saveGameReview(review: Review): Flow<Response<Boolean>>
    fun deleteGame(game: Game): Flow<Response<Boolean>>
}
