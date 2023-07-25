package com.dnnsgnzls.modern.data.api.games

import com.dnnsgnzls.modern.data.api.filters.FiltersDto
import com.google.gson.annotations.SerializedName

data class GamesDto(
    val count: Long,
    val next: String?,
    val previous: String?,
    val results: List<GameDto>,
    @SerializedName("seo_title")
    val seoTitle: String,
    @SerializedName("seo_description")
    val seoDescription: String,
    @SerializedName("seo_keywords")
    val seoKeywords: String,
    @SerializedName("seo_h1")
    val seoH1: String,
    val noindex: Boolean,
    val nofollow: Boolean,
    val description: String,
    val filters: FiltersDto,
    @SerializedName("nofollow_collections")
    val nofollowCollections: List<String>
)


