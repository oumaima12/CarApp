package com.example.carapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carapp.data.model.MakeResponse
import com.example.carapp.domain.usecase.GetCarMakesUseCase
import com.example.carapp.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CarViewModel @Inject constructor(
    private val getCarMakesUseCase: GetCarMakesUseCase
) : ViewModel() {

    private val _carMakes = MutableStateFlow<ApiResult<MakeResponse>>(ApiResult.Loading)
    val carMakes: StateFlow<ApiResult<MakeResponse>> = _carMakes

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    fun fetchCarMakes() {
        viewModelScope.launch {
            _isRefreshing.value = true // Start refreshing
            getCarMakesUseCase().collect { result ->
                _carMakes.value = result
                _isRefreshing.value = false // Stop refreshing
            }
        }
    }
}

