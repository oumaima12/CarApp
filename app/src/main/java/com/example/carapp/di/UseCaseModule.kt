package com.example.carapp.di

import com.example.carapp.domain.CarRepository
import com.example.carapp.domain.usecase.GetCarMakesUseCase
import com.example.carapp.domain.usecase.GetCarModelsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetCarMakesUseCase(repository: CarRepository): GetCarMakesUseCase =
        GetCarMakesUseCase(repository)

    @Provides
    @Singleton
    fun provideGetCarModelsUseCase(repository: CarRepository): GetCarModelsUseCase =
        GetCarModelsUseCase(repository)
}