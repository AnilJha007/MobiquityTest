package com.mobile.mobiquityassignment.service.api

import com.mobile.mobiquityassignment.service.model.ForecastResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getTodayForecast(latitude: Double, longitude: Double): Response<ForecastResponse>
}