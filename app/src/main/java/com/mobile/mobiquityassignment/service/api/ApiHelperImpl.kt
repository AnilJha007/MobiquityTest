package com.mobile.mobiquityassignment.service.api

import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getTodayForecast(
        latitude: Double,
        longitude: Double
    ) = apiService.getTodayForecast(latitude, longitude)
}