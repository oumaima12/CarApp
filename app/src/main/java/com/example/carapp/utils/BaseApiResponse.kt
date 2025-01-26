package com.example.carapp.utils

import com.example.carapp.data.model.MakeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseApiResponse {
    protected fun <T> handleResponse(apiCall: suspend () -> Response<T>): Flow<ApiResult<T>> = flow {
        emit(ApiResult.Loading)
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.let {
                    if (it is MakeResponse && it.makes.isNotEmpty()) {
                        emit(ApiResult.Success(it))
                    } else {
                        emit(ApiResult.Error("Empty list received"))
                    }
                } ?: emit(ApiResult.Error("Empty response body"))
            } else {
                emit(ApiResult.Error("API Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(ApiResult.Error("Exception: ${e.localizedMessage}"))
        }
    }

}
