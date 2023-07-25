package com.dnnsgnzls.modern.data.api.games

import com.dnnsgnzls.modern.data.api.esbr.EsrbRatingDto
import com.dnnsgnzls.modern.data.api.genre.GenreDto
import com.dnnsgnzls.modern.data.api.platforms.ParentPlatformDto
import com.dnnsgnzls.modern.data.api.platforms.PlatformElementDto
import com.dnnsgnzls.modern.data.api.screenshots.ShortScreenshotDto
import com.dnnsgnzls.modern.data.api.stores.StoreShortInfoDto
import com.dnnsgnzls.modern.data.api.color.ColorDto
import com.google.gson.annotations.SerializedName

data class GameDto(
    val id: Long,
    val slug: String,
    val name: String,
    val released: String,
    val tba: Boolean,
    @SerializedName("background_image")
    val backgroundImage: String,
    val rating: Double,
    @SerializedName("rating_top")
    val ratingTop: Long,
    val ratings: List<RatingDto>,
    @SerializedName("ratings_count")
    val ratingsCount: Long,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: Long,
    val added: Long,
    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatusDto,
    val metacritic: Long,
    val playtime: Long,
    @SerializedName("suggestions_count")
    val suggestionsCount: Long,
    val updated: String,
    @SerializedName("user_game")
    val userGame: Any? = null,
    @SerializedName("reviews_count")
    val reviewsCount: Long,
    @SerializedName("saturated_color")
    val saturatedColor: ColorDto,
    @SerializedName("dominant_color")
    val dominantColor: ColorDto,
    val platforms: List<PlatformElementDto>,
    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatformDto>,
    val genres: List<GenreDto>,
    val stores: List<StoreShortInfoDto>,
    val clip: Any? = null,
    val tags: List<GenreDto>,
    @SerializedName("esrb_rating")
    val esrbRating: EsrbRatingDto,
    @SerializedName("short_screenshots")
    val shortScreenshots: List<ShortScreenshotDto>
)