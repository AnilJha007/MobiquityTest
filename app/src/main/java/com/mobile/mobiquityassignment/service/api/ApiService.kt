package com.mobile.mobiquityassignment.service.api

import com.mobile.mobiquityassignment.BuildConfig
import com.mobile.mobiquityassignment.service.model.FiveDaysForecastResponse
import com.mobile.mobiquityassignment.service.model.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/weather/")
    suspend fun getTodayForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = BuildConfig.API_KEY,
    ): Response<ForecastResponse>

    @GET("data/2.5/forecast/")
    suspend fun getFiveDaysForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = BuildConfig.API_KEY,
        @Query("units") unit: String = "metric",
    ): Response<FiveDaysForecastResponse>
}