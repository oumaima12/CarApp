package com.example.carapp.data

import com.example.carapp.data.model.MakeResponse
import com.example.carapp.data.model.ModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CarApiService {

    @GET("?cmd=getMakes")
    suspend fun getCarMakes(): Response<MakeResponse>

    @GET("?cmd=getModels")
    suspend fun getCarModels(@Query("make") make: String): Response<ModelResponse>
}