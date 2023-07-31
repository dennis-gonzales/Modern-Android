package com.dnnsgnzls.modern.data.api


data class GamesDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<GameDto>
)


