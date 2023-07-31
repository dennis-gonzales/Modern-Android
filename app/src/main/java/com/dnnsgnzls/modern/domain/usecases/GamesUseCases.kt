package com.dnnsgnzls.modern.domain.usecases

class GamesUseCases(
    val getGameUseCase: GetGameUseCase,
    val getGamesUseCase: GetGamesUseCase,
    val getFavouriteGamesUseCase: GetFavouriteGamesUseCase,
    val getFavouriteGameIdsUseCase: GetFavouriteGameIdsUseCase,
    val getGameReviewsUseCase: GetGameReviewsUseCase,
    val saveGameUseCase: SaveGameUseCase,
    val saveGameReviewUseCase: SaveGameReviewUseCase,
    val deleteGameUseCase: DeleteGameUseCase

)