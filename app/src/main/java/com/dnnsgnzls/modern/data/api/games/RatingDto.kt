package com.dnnsgnzls.modern.data.api.games

data class RatingDto(
    val id: Long,
    val title: TitleDto,
    val count: Long,
    val percent: Double
)