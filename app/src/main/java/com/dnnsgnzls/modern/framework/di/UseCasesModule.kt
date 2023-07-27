package com.dnnsgnzls.modern.framework.di

import com.dnnsgnzls.modern.data.usecases.GetGamesUseCaseImpl
import com.dnnsgnzls.modern.data.usecases.SaveGameUseCaseImpl
import com.dnnsgnzls.modern.data.usecases.SaveGamesUseCaseImpl
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
            GetGamesUseCaseImpl(gamesRepository),
            SaveGameUseCaseImpl(gamesRepository),
            SaveGamesUseCaseImpl(gamesRepository)
        )
    }
}