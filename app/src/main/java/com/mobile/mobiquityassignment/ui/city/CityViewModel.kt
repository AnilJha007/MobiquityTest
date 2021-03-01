package com.mobile.mobiquityassignment.ui.city

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.mobile.mobiquityassignment.R
import com.mobile.mobiquityassignment.service.model.ErrorResponse
import com.mobile.mobiquityassignment.service.model.FiveDaysForecastResponse
import com.mobile.mobiquityassignment.service.model.ForecastResponse
import com.mobile.mobiquityassignment.service.repository.MobiquityRepository
import com.mobile.mobiquityassignment.service.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    @ApplicationContext private val ctx: Context,
    private val repository: MobiquityRepository
) : ViewModel() {

    private val _todayForecastMutableLiveData by lazy { MutableLiveData<Resource<ForecastResponse>>() }

    private val _fiveDaysForecastMutableLiveData by lazy { MutableLiveData<Resource<FiveDaysForecastResponse>>() }

    var isApiLoadedOnce = false

    val todayForecastLiveData: LiveData<Resource<ForecastResponse>>
        get() = _todayForecastMutableLiveData

    val fiveDaysForecastLiveData: LiveData<Resource<FiveDaysForecastResponse>>
        get() = _fiveDaysForecastMutableLiveData

    fun getTodayForecast(lat: Double, long: Double) {
        with(_todayForecastMutableLiveData) {
            value = Resource.loading(null)
            viewModelScope.launch {
                val response = repository.getTodayForecast(lat, long)
                response.takeIf { it.isSuccessful }?.let { res ->
                    res.body()?.let {
                        value = Resource.success(it)
                    } ?: run {
                        value = Resource.error(ctx.getString(R.string.something_went_wrong), null)
                    }
                } ?: run {
                    response.errorBody()?.let {
                        val errorResponse =
                            Gson().fromJson(it.charStream(), ErrorResponse::class.java)
                        value = Resource.error(errorResponse.message, null)
                    }
                }
            }
        }
    }

    fun getFiveDaysForecast(lat: Double, long: Double) {
        with(_fiveDaysForecastMutableLiveData) {
            value = Resource.loading(null)
            viewModelScope.launch {
                val response = repository.getFiveDaysForecast(lat, long)
                response.takeIf { it.isSuccessful }?.let { res ->
                    res.body()?.let {
                        value = Resource.success(it)
                    } ?: run {
                        value = Resource.error(ctx.getString(R.string.something_went_wrong), null)
                    }
                } ?: run {
                    response.errorBody()?.let {
                        val errorResponse =
                            Gson().fromJson(it.charStream(), ErrorResponse::class.java)
                        value = Resource.error(errorResponse.message, null)
                    }
                }
            }
        }
    }
}