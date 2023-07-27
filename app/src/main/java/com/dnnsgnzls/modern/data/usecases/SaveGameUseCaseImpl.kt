package com.dnnsgnzls.modern.data.usecases

import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.domain.usecases.SaveGameUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

class SaveGameUseCaseImpl(
    private val gamesRepository: GamesRepository
) : SaveGameUseCase {
    override operator fun invoke(game: Game): Flow<Response<Boolean>> {
        return gamesRepository.saveGame(game)
    }
}
