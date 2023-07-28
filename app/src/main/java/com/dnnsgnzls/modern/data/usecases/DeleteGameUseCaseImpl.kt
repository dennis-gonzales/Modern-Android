package com.dnnsgnzls.modern.data.usecases

import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.domain.usecases.DeleteGameUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

class DeleteGameUseCaseImpl(
    private val gamesRepository: GamesRepository
): DeleteGameUseCase {
    override fun invoke(game: Game): Flow<Response<Boolean>> {
        return gamesRepository.deleteGame(game)
    }
}