package com.dnnsgnzls.modern.data.usecases

import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.domain.usecases.GetFavouriteGamesUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

class GetFavouriteGamesUseCaseImpl(
    private val gamesRepository: GamesRepository
) : GetFavouriteGamesUseCase {
    override fun invoke(): Flow<Response<List<Game>>> {
        return gamesRepository.getFavouriteGames()
    }
}