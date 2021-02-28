package com.mobile.mobiquityassignment.service.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey val id: Long,
    val latitude: Double,
    val longitude: Double,
    val cityName: String
)