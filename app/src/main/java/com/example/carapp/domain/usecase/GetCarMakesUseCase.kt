package com.example.carapp.domain.usecase

import com.example.carapp.data.model.MakeResponse
import com.example.carapp.domain.CarRepository
import com.example.carapp.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCarMakesUseCase @Inject constructor (private val repository: CarRepository) {
    operator fun invoke(): Flow<ApiResult<MakeResponse>> = repository.getCarMakes()
}