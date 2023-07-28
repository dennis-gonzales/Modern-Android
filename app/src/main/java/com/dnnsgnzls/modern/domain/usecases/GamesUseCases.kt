package com.dnnsgnzls.modern.domain.usecases

class GamesUseCases(
    val getGameUseCase: GetGameUseCase,
    val getGamesUseCase: GetGamesUseCase,
    val getFavouriteGameIdsUseCase: GetFavouriteGameIdsUseCase,
    val saveGameUseCase: SaveGameUseCase,
    val saveGamesUseCase: SaveGamesUseCase,
    val deleteGameUseCase: DeleteGameUseCase

)