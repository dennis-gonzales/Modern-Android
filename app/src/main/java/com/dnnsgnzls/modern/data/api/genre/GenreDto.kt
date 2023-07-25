package com.dnnsgnzls.modern.data.api.genre

import com.dnnsgnzls.modern.data.api.language.LanguageDto
import com.dnnsgnzls.modern.data.api.domain.DomainDto
import com.google.gson.annotations.SerializedName

data class GenreDto(
    val id: Long,
    val name: String,
    val slug: String,
    @SerializedName("games_count")
    val gamesCount: Long,
    @SerializedName("image_background")
    val imageBackground: String,
    val domain: DomainDto? = null,
    val language: LanguageDto? = null
)