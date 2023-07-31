package com.dnnsgnzls.modern.framework.di

import com.dnnsgnzls.modern.data.usecases.DeleteGameUseCaseImpl
import com.dnnsgnzls.modern.data.usecases.GetFavouriteGameIdsImpl
import com.dnnsgnzls.modern.data.usecases.GetFavouriteGamesUseCaseImpl
import com.dnnsgnzls.modern.data.usecases.GetGameReviewsUseCaseimpl
import com.dnnsgnzls.modern.data.usecases.GetGameUseCaseImpl
import com.dnnsgnzls.modern.data.usecases.GetGamesUseCaseImpl
import com.dnnsgnzls.modern.data.usecases.SaveGameUseCaseImpl
import com.dnnsgnzls.modern.data.usecases.SaveGameReviewUseCaseImpl
import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.domain.usecases.GamesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    /// <- Parameter`gamesRepository` is provided in `RepositoryModule.kt` ->
    @Singleton
    @Provides
    fun provideGamesUseCases(gamesRepository: GamesRepository): GamesUseCases {
        return GamesUseCases(
            GetGameUseCaseImpl(gamesRepository),
            GetGamesUseCaseImpl(gamesRepository),
            GetFavouriteGamesUseCaseImpl(gamesRepository),
            GetFavouriteGameIdsImpl(gamesRepository),
            GetGameReviewsUseCaseimpl(gamesRepository),
            SaveGameUseCaseImpl(gamesRepository),
            SaveGameReviewUseCaseImpl(gamesRepository),
            DeleteGameUseCaseImpl(gamesRepository)
        )
    }
}