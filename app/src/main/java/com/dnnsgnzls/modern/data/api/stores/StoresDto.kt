package com.dnnsgnzls.modern.data.api.stores

data class StoresDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<StoreDto>
)