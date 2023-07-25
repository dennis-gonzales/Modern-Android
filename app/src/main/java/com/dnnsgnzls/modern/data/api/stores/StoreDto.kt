package com.dnnsgnzls.modern.data.api.stores

import com.dnnsgnzls.modern.data.api.games.GameShortInfoDto
import com.google.gson.annotations.SerializedName

data class StoreDto(
    val id: Int,
    val name: String,
    val domain: String,
    val slug: String,
    @SerializedName("games_count")
    val gamesCount: Int,
    @SerializedName("image_background")
    val imageBackground: String,
    val games: List<GameShortInfoDto>
)