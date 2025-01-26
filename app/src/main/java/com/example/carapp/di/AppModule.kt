package com.example.carapp.di

import com.example.carapp.data.CarApiService
import com.example.carapp.data.CarRepositoryImpl
import com.example.carapp.domain.CarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCarRepository(api: CarApiService): CarRepository {
        return CarRepositoryImpl(api)
    }
}
