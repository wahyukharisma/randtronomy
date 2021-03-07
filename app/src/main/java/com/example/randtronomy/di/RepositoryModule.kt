package com.example.randtronomy.di

import com.example.randtronomy.repository.MainRepository
import com.example.randtronomy.services.network.AstronomyServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        astronomyServices: AstronomyServices
    ) : MainRepository {
        return MainRepository(
            astronomyServices
        )
    }
}