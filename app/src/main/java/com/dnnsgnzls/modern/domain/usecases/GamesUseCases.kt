package com.dnnsgnzls.modern.domain.usecases

class GamesUseCases(
    val getGameUseCase: GetGameUseCase,
    val getGamesUseCase: GetGamesUseCase,
    val getFavouriteGamesUseCase: GetFavouriteGamesUseCase,
    val getFavouriteGameIdsUseCase: GetFavouriteGameIdsUseCase,
    val getGameReviewsByGameIdUseCase: GetGameReviewsByGameIdUseCase,
    val saveGameUseCase: SaveGameUseCase,
    val saveGameReviewUseCase: SaveGameReviewUseCase,
    val deleteGameUseCase: DeleteGameUseCase,
    val deleteGameReviewUseCase: DeleteGameReviewUseCase,
    val deleteGamesReviewsByGameIdUseCase: DeleteGameReviewsByGameIdUseCase
)