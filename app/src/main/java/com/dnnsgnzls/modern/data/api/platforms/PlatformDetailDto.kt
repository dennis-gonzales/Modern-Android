package com.dnnsgnzls.modern.data.api.platforms

import com.google.gson.annotations.SerializedName

data class PlatformDetailDto(
    val id: Long,
    val name: String,
    val slug: String,
    val image: String? = null,
    @SerializedName("year_end")
    val yearEnd: Long? = null,
    @SerializedName("year_start")
    val yearStart: Long? = null,
    @SerializedName("games_count")
    val gamesCount: Long,
    @SerializedName("image_background")
    val imageBackground: String
)