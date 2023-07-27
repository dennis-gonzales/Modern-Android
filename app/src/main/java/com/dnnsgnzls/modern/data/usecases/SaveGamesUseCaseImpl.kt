package com.dnnsgnzls.modern.data.usecases

import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.domain.usecases.SaveGamesUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

class SaveGamesUseCaseImpl(
    private val gamesRepository: GamesRepository
) : SaveGamesUseCase {
    override operator fun invoke(games: List<Game>): Flow<Response<Boolean>> {
        return gamesRepository.saveGames(games)
    }
}
