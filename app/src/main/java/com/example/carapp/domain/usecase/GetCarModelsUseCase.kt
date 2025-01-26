package com.example.carapp.domain.usecase

import com.example.carapp.data.model.ModelResponse
import com.example.carapp.domain.CarRepository
import com.example.carapp.utils.ApiResult
import kotlinx.coroutines.flow.Flow

class GetCarModelsUseCase(private val repository: CarRepository) {
    operator fun invoke(make: String): Flow<ApiResult<ModelResponse>> = repository.getCarModels(make)
}