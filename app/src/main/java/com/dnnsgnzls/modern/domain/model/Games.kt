package com.dnnsgnzls.modern.domain.model

data class Games(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Game>
)
