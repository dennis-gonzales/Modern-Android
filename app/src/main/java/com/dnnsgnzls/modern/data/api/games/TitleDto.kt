package com.dnnsgnzls.modern.data.api.games

enum class TitleDto(val value: String) {
    Exceptional("exceptional"),
    Meh("meh"),
    Recommended("recommended"),
    Skip("skip");

    companion object {
        fun fromValue(value: String): TitleDto = when (value) {
            "exceptional" -> Exceptional
            "meh" -> Meh
            "recommended" -> Recommended
            "skip" -> Skip
            else -> throw IllegalArgumentException("Unknown title: $value")
        }
    }
}