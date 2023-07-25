package com.dnnsgnzls.modern.data.api.language

enum class LanguageDto(val value: String) {
    English("eng");

    companion object {
        fun fromValue(value: String): LanguageDto = when (value) {
            "eng" -> English
            else -> throw IllegalArgumentException("Unknown language: $value")
        }
    }
}