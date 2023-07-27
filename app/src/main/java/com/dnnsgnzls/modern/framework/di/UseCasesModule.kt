package com.dnnsgnzls.modern.framework.di

import com.dnnsgnzls.modern.data.usecases.GamesUseCaseImpl
import com.dnnsgnzls.modern.domain.repository.RawgRepository
import com.dnnsgnzls.modern.domain.usecases.GamesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    /// <- Parameter`rawgRepository` is provided in `RepositoryModule.kt` ->
    /// < The `UseCasesModule` are being injected to `ViewModels` ->
    @Provides
    fun provideGamesUseCases(rawgRepository: RawgRepository): GamesUseCases {
        return GamesUseCases(GamesUseCaseImpl(rawgRepository))
    }
}