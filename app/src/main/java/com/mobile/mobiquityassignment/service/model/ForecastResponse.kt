package com.mobile.mobiquityassignment.service.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    val weather: ArrayList<Weather>,
    val main: Main,
    val wind: Wind,
    val sys: Sys
)

data class Weather(val id: Long, val main: String, val description: String)

data class Main(
    @SerializedName("temp_min")
    val minTemp: Float,
    @SerializedName("temp_max")
    val maxTemp: Float,
    val pressure: Int,
    val humidity: Int
)

data class Wind(val speed: Float)

data class Sys(val sunrise: Long, val sunset: Long)