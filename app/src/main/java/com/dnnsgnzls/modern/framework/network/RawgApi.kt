package com.dnnsgnzls.modern.framework.network

import com.dnnsgnzls.modern.data.api.games.GamesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RawgApi {
    @GET("/api/games")
    suspend fun games(
        @Query("search") searchQuery: String,
        @Query("page") page: Int,
        @Query("search_precise") searchPrecise: Boolean = true
    ): GamesDto
}