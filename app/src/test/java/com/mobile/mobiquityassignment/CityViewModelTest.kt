package com.mobile.mobiquityassignment

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mobile.mobiquityassignment.service.model.*
import com.mobile.mobiquityassignment.service.repository.MobiquityRepository
import com.mobile.mobiquityassignment.service.utility.NetworkHelper
import com.mobile.mobiquityassignment.service.utility.Resource
import com.mobile.mobiquityassignment.ui.city.CityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CityViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var repository: MobiquityRepository

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var todayForecastObserver: Observer<Resource<ForecastResponse>>

    @Mock
    private lateinit var fiveDaysForecastObserver: Observer<Resource<FiveDaysForecastResponse>>

    @Test
    fun testTodayForecastSuccessScenario() {
        testCoroutineRule.runBlockingTest {
            `when`(networkHelper.isNetworkConnected()).thenReturn(true)
            doReturn(getForecastData())
                .`when`(repository)
                .getTodayForecast(17.387140, 78.491684)
            val viewModel = CityViewModel(context, repository, networkHelper)
            viewModel.todayForecastLiveData.observeForever(todayForecastObserver)
            verify(repository).getTodayForecast(17.387140, 78.491684)
            verify(todayForecastObserver).onChanged(Resource.success(getForecastData()))
            viewModel.todayForecastLiveData.removeObserver(todayForecastObserver)
        }
    }

    @Test
    fun testTodayForecastErrorScenario() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error is today forecast api"
            doThrow(RuntimeException(errorMessage))
                .`when`(repository)
                .getTodayForecast(17.387140, 78.491684)
            val viewModel = CityViewModel(context, repository, networkHelper)
            viewModel.todayForecastLiveData.observeForever(todayForecastObserver)
            verify(repository).getTodayForecast(17.387140, 78.491684)
            verify(todayForecastObserver).onChanged(
                Resource.error(
                    RuntimeException(errorMessage).toString(),
                    null
                )
            )
            viewModel.todayForecastLiveData.removeObserver(todayForecastObserver)
        }
    }

    @Test
    fun testFiveDaysForecastSuccessScenario() {
        testCoroutineRule.runBlockingTest {
            `when`(networkHelper.isNetworkConnected()).thenReturn(true)
            doReturn(getFiveDaysForecastData())
                .`when`(repository)
                .getFiveDaysForecast(17.387140, 78.491684)
            val viewModel = CityViewModel(context, repository, networkHelper)
            viewModel.fiveDaysForecastLiveData.observeForever(fiveDaysForecastObserver)
            verify(repository).getTodayForecast(17.387140, 78.491684)
            verify(fiveDaysForecastObserver).onChanged(Resource.success(getFiveDaysForecastData()))
            viewModel.fiveDaysForecastLiveData.removeObserver(fiveDaysForecastObserver)
        }
    }

    @Test
    fun testFiveDaysForecastErrorScenario() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error in five days forecast response"
            doThrow(RuntimeException(errorMessage))
                .`when`(repository)
                .getFiveDaysForecast(17.387140, 78.491684)
            val viewModel = CityViewModel(context, repository, networkHelper)
            viewModel.fiveDaysForecastLiveData.observeForever(fiveDaysForecastObserver)
            verify(repository).getFiveDaysForecast(17.387140, 78.491684)
            verify(fiveDaysForecastObserver).onChanged(
                Resource.error(
                    RuntimeException(errorMessage).toString(),
                    null
                )
            )
            viewModel.fiveDaysForecastLiveData.removeObserver(fiveDaysForecastObserver)
        }
    }


    private fun getForecastData() =
        ForecastResponse(
            arrayListOf(),
            Main(23.43f, 12.00f, 40.00f, 43, 44),
            Wind(2.54f),
            Sys(31412312231, 1413243124),
            "2021-29-21"
        )

    private fun getFiveDaysForecastData() = FiveDaysForecastResponse(arrayListOf(getForecastData()))

}