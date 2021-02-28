package com.mobile.mobiquityassignment.service.repository

import android.content.Context
import com.mobile.mobiquityassignment.service.database.dao.CityDao
import com.mobile.mobiquityassignment.service.database.entity.City
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MobiquityRepository @Inject constructor(
    @ApplicationContext private val ctx: Context,
    private val cityDao: CityDao
) {

    fun getAllCities() = cityDao.getAllCities()

    suspend fun addCity(city: City) = cityDao.insertCity(city)

    suspend fun deleteCity(id: Long) = cityDao.deleteCityById(id)
}