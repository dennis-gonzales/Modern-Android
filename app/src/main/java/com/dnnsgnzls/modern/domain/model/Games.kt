package com.dnnsgnzls.modern.domain.model

data class Games(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Game>
)

fun Games.updateWith(newData: Games): Games {
    return Games(
        count = newData.count,
        next = newData.next,
        previous = newData.previous,
        results = this.results + newData.results
    )
}
