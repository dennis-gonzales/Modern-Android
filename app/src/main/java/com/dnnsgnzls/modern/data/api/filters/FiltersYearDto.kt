package com.dnnsgnzls.modern.data.api.filters

data class FiltersYearDto(
    val from: Long,
    val to: Long,
    val filter: String,
    val decade: Long,
    val years: List<YearDto>,
    val nofollow: Boolean,
    val count: Long
)