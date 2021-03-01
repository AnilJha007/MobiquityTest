package com.mobile.mobiquityassignment.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.mobiquityassignment.service.database.entity.City
import com.mobile.mobiquityassignment.service.repository.MobiquityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MobiquityRepository) : ViewModel() {

    fun getAllCities() = repository.getAllCities()

    fun getCityById(id: Long) = repository.getCityById(id)

    fun addCity(city: City) {
        viewModelScope.launch { repository.addCity(city) }
    }

    fun deleteCityById(id: Long) {
        viewModelScope.launch { repository.deleteCity(id) }
    }
}