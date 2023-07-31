package com.dnnsgnzls.modern.framework.di

import android.content.Context
import com.dnnsgnzls.modern.data.repository.GamesRepositoryImpl
import com.dnnsgnzls.modern.domain.repository.GamesRepository
import com.dnnsgnzls.modern.framework.network.ApiService
import com.dnnsgnzls.modern.framework.network.RawgApi
import com.dnnsgnzls.modern.framework.persistence.DatabaseService
import com.dnnsgnzls.modern.framework.persistence.GameDao
import com.dnnsgnzls.modern.framework.persistence.ReviewDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /// <- `ApiService` is already a `Singleton` - no need for singleton annotation ->
    @Provides
    fun provideRawgApi(): RawgApi = ApiService.api

    /// <- As `Dispatchers.IO` is a shared instance - no need for singleton annotation ->
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    /// <- `DatabaseService` is a singleton to ensure data consistency and avoid DB locks ->
    @Singleton
    @Provides
    fun provideDatabaseService(@ApplicationContext context: Context): DatabaseService {
        return DatabaseService(context)
    }

    /// <- `GameDao` is a singleton and it's lifecycle matches its parent `DatabaseService` ->
    @Singleton
    @Provides
    fun provideGameDao(databaseService: DatabaseService): GameDao {
        return databaseService.gameDao()
    }

    /// <- `ReviewDao` is a singleton and it's lifecycle matches its parent `DatabaseService` ->
    @Singleton
    @Provides
    fun provideReviewDao(databaseService: DatabaseService): ReviewDao {
        return databaseService.reviewDao()
    }

    /// <- Provides a `GamesRepository` to be used by `UseCasesModule.kt` ->
    @Singleton
    @Provides
    fun provideGamesRepository(
        rawgApi: RawgApi,
        gameDao: GameDao,
        reviewDao: ReviewDao,
        coroutineDispatcher: CoroutineDispatcher
    ): GamesRepository {
        return GamesRepositoryImpl(rawgApi, gameDao, reviewDao, coroutineDispatcher)
    }
}