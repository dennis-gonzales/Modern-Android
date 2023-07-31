package com.dnnsgnzls.modern.domain.mapper

import com.dnnsgnzls.modern.data.api.GameDto
import com.dnnsgnzls.modern.data.api.GamesDto
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.model.Games

fun mapGamesFromDto(gamesDto: GamesDto): Games {
    return Games(
        count = gamesDto.count,
        next = gamesDto.next,
        previous = gamesDto.previous,
        results = gamesDto.results.map { mapGameFromDto(it) }
    )
}

fun mapGameFromDto(gameDto: GameDto): Game {
    return Game(
        id = gameDto.id,
        slug = gameDto.slug,
        name = gameDto.name,
        released = gameDto.released,
        backgroundImage = gameDto.backgroundImage,
        rating = gameDto.rating,
    )
}
