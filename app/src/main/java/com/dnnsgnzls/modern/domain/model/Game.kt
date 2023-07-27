package com.dnnsgnzls.modern.domain.model

data class Game(
    val id: Long,
    val slug: String,
    val name: String,
    val released: String,
    val backgroundImage: String,
    val rating: Double?
)
