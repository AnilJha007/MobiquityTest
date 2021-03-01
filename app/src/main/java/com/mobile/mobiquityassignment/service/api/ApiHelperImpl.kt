package com.mobile.mobiquityassignment.service.api

import com.mobile.mobiquityassignment.service.model.FiveDaysForecastResponse
import com.mobile.mobiquityassignment.service.model.ForecastResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getTodayForecast(
        latitude: Double,
        longitude: Double
    ) = apiService.getTodayForecast(latitude, longitude)

    override suspend fun getFiveDaysForecast(
        latitude: Double,
        longitude: Double
    ): Response<FiveDaysForecastResponse> = apiService.getFiveDaysForecast(latitude, longitude)
}