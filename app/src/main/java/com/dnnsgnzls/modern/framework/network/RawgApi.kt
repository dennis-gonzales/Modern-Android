package com.dnnsgnzls.modern.framework.network

import com.dnnsgnzls.modern.data.api.GameDto
import com.dnnsgnzls.modern.data.api.GamesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RawgApi {
    @GET("/api/games")
    suspend fun games(
        @Query("search") searchQuery: String,
        @Query("page") page: Int,
        @Query("search_precise") searchPrecise: Boolean = true
    ): GamesDto

    @GET("/api/games/{id}")
    suspend fun gameDetails(
        @Path("id") id: String
    ): GameDto
}