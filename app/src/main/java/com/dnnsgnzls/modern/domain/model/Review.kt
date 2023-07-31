package com.dnnsgnzls.modern.domain.model

data class Review(
    val gameId: Long,
    val title: String,
    val text: String,
    val id: Long = 0
)