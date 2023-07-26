package com.dnnsgnzls.modern.framework.di

import com.dnnsgnzls.modern.data.repository.RawgRepositoryImpl
import com.dnnsgnzls.modern.domain.repository.RawgRepository
import com.dnnsgnzls.modern.framework.network.ApiService
import com.dnnsgnzls.modern.framework.network.RawgApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

    @Singleton
    @Provides
    fun provideRawgRepository(
        rawgApi: RawgApi,
        coroutineDispatcher: CoroutineDispatcher
    ): RawgRepository {
        return RawgRepositoryImpl(rawgApi, coroutineDispatcher)
    }
}