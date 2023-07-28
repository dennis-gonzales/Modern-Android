package com.dnnsgnzls.modern.data.usecases

import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.domain.usecases.GetFavouriteGameIdsUseCase
import com.dnnsgnzls.modern.framework.utils.Response
import kotlinx.coroutines.flow.Flow

class GetFavouriteGameIdsImpl(
    private val gamesRepository: GamesRepository
) : GetFavouriteGameIdsUseCase {
    override fun invoke(): Flow<Response<List<Long>>> {
        return gamesRepository.getFavouriteGameIds()
    }
}