package com.example.carapp

import com.example.carapp.domain.usecase.GetCarMakesUseCase
import com.example.carapp.utils.ApiResult
import com.example.carapp.data.model.MakeResponse
import com.example.carapp.data.model.CarMake
import com.example.carapp.presentation.CarViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class CarViewModelTest {

    private lateinit var viewModel: CarViewModel
    private lateinit var getCarMakesUseCase: GetCarMakesUseCase
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Set the main dispatcher for testing
        getCarMakesUseCase = mock() // Mock the use case
        viewModel = CarViewModel(getCarMakesUseCase) // Initialize the ViewModel
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher
    }

    @Test
    fun `fetchCarMakes should update carMakes with success result`() = runTest {
        // Arrange
        val carMakes = listOf(
            CarMake("honda", "Honda", "0", "Japan"),
            CarMake("toyota", "Toyota", "1", "Japan")
        )
        val makeResponse = MakeResponse(carMakes)
        val flow = flow { emit(ApiResult.Success(makeResponse)) }

        `when`(getCarMakesUseCase()).thenReturn(flow)

        // Act
        viewModel.fetchCarMakes()

        // Assert
        val result = viewModel.carMakes.value
        assertTrue(result is ApiResult.Success)
        assertEquals(carMakes, (result as ApiResult.Success).data.makes)
    }

    @Test
    fun `fetchCarMakes should update carMakes with error result`() = runTest {
        // Arrange
        val errorMessage = "An error occurred"
        val flow = flow { emit(ApiResult.Error(errorMessage)) }

        `when`(getCarMakesUseCase()).thenReturn(flow)

        // Act
        viewModel.fetchCarMakes()

        // Assert
        val result = viewModel.carMakes.value
        assertTrue(result is ApiResult.Error)
        assertEquals(errorMessage, (result as ApiResult.Error).message)
    }

    @Test
    fun `fetchCarMakes should update isRefreshing state`() = runTest {
        // Arrange
        val carMakes = listOf(
            CarMake("honda", "Honda", "0", "Japan"),
            CarMake("toyota", "Toyota", "1", "Japan")
        )
        val makeResponse = MakeResponse(carMakes)
        val flow = flow { emit(ApiResult.Success(makeResponse)) }

        `when`(getCarMakesUseCase()).thenReturn(flow)

        // Act
        viewModel.fetchCarMakes()

        // Assert
        assertFalse(viewModel.isRefreshing.value) // isRefreshing should be false after fetching
    }
}