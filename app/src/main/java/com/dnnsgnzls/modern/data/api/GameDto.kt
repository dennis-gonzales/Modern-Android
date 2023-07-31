package com.dnnsgnzls.modern.data.api

import com.google.gson.annotations.SerializedName

data class GameDto(
    val id: Long,
    val slug: String,
    val name: String,
    val released: String?,
    @SerializedName("background_image")
    val backgroundImage: String?,
    val rating: Double?,
)