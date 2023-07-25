package com.dnnsgnzls.modern.data.api.games

data class AddedByStatusDto(
    val yet: Long,
    val owned: Long,
    val beaten: Long,
    val toplay: Long,
    val dropped: Long,
    val playing: Long
)