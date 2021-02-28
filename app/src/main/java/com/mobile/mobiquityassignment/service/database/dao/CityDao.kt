package com.mobile.mobiquityassignment.service.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.mobiquityassignment.service.database.entity.City

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCities(cities: MutableList<City>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: City): Long

    @Query("SELECT * FROM city")
    fun getAllCities(): LiveData<MutableList<City>>

    @Query("SELECT * FROM city WHERE id = :id")
    fun getCityById(id: Long): LiveData<City>

    @Query("DELETE FROM city WHERE id = :id")
    fun deleteCityById(id: Long)
}