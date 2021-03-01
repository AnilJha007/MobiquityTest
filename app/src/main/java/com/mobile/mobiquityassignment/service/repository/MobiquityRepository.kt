package com.mobile.mobiquityassignment.service.repository

import com.mobile.mobiquityassignment.service.api.ApiHelper
import com.mobile.mobiquityassignment.service.database.dao.CityDao
import com.mobile.mobiquityassignment.service.database.entity.City
import javax.inject.Inject

class MobiquityRepository @Inject constructor(
    private val cityDao: CityDao,
    private val apiHelper: ApiHelper
) {

    fun getAllCities() = cityDao.getAllCities()

    fun getCityById(id: Long) = cityDao.getCityById(id)

    suspend fun addCity(city: City) = cityDao.insertCity(city)

    suspend fun deleteCity(id: Long) = cityDao.deleteCityById(id)

    suspend fun getTodayForecast(lat: Double, long: Double) = apiHelper.getTodayForecast(lat, long)
}