package com.example.carapp.data

import com.example.carapp.data.model.MakeResponse
import com.example.carapp.data.model.ModelResponse
import com.example.carapp.domain.CarRepository
import com.example.carapp.utils.ApiResult
import com.example.carapp.utils.BaseApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(private val api: CarApiService) : BaseApiResponse(),
    CarRepository {
    override fun getCarMakes(): Flow<ApiResult<MakeResponse>> = handleResponse {
        val response = api.getCarMakes()
        if (response.isSuccessful) {
            println("API Response: ${response.body()}")
        } else {
            println("API Error: ${response.message()}")
        }
        response
    }
    override fun getCarModels(make: String): Flow<ApiResult<ModelResponse>> = handleResponse { api.getCarModels(make) }
}
