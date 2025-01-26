package com.example.carapp.domain

import com.example.carapp.data.model.MakeResponse
import com.example.carapp.data.model.ModelResponse
import com.example.carapp.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    fun getCarMakes(): Flow<ApiResult<MakeResponse>>
    fun getCarModels(make: String): Flow<ApiResult<ModelResponse>>
}