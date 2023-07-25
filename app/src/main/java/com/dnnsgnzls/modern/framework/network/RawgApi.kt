package com.dnnsgnzls.modern.framework.network

import com.dnnsgnzls.modern.data.api.games.GamesDto
import retrofit2.http.GET

interface RawgApi {
    @GET("/games")
    suspend fun games(): List<GamesDto>
}