package com.mobile.mobiquityassignment.ui.city

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mobile.mobiquityassignment.R
import com.mobile.mobiquityassignment.base.BaseFragment
import com.mobile.mobiquityassignment.service.model.ForecastResponse
import com.mobile.mobiquityassignment.service.utility.ApiStatus
import com.mobile.mobiquityassignment.ui.home.HomeViewModel
import com.mobile.mobiquityassignment.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_city.*

@AndroidEntryPoint
class CityFragment : BaseFragment() {

    companion object {
        const val CITY_ID_ARG = "cityIdArgument"
    }

    private var cityId: Long? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private val cityViewModel: CityViewModel by viewModels()

    override fun getLayoutResourceId() = R.layout.fragment_city

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityId = arguments?.getLong(CITY_ID_ARG)
        initViewModel()
    }

    private fun initViewModel() {
        cityId?.let {
            homeViewModel.getCityById(it).observe(viewLifecycleOwner, { city ->
                setPageTitle(city.cityName)
                cityViewModel.getTodayForecast(
                    city.latitude,
                    city.longitude
                ) // calling today forecast api
            })
        }

        cityViewModel.todayForecastLiveData.observe(viewLifecycleOwner, { resources ->
            when (resources.status) {
                ApiStatus.LOADING -> {
                    if (!cityViewModel.isApiLoadedOnce) {
                        showDialog()
                        cityViewModel.isApiLoadedOnce = true
                    }
                }
                ApiStatus.SUCCESS -> {
                    hideDialog()
                    setData(resources.data)
                }
                ApiStatus.ERROR -> {
                    hideDialog()
                    resources.message?.let {
                        constraintCity.snackBar(it)
                    }
                }
            }
        })
    }

    private fun setData(data: ForecastResponse?) {
        data?.let {
            cardForecast.show()

            tvWind.setTextOrHide(
                getString(
                    R.string.wind_info,
                    String.format("%.2f", it.wind.speed)
                )
            )
            with(it.main) {
                tvTemperature.setTextOrHide(getTemperature(temp))
                ivMinTemp.show()
                tvMinTemp.setTextOrHide(getTemperature(minTemp))
                ivMaxTemp.show()
                tvMaxTemp.setTextOrHide(getTemperature(maxTemp))

                tvHumidity.setTextOrHide(getString(R.string.humidity_info, humidity))
            }

            with(it.sys) {
                tvSunrise.setTextOrHide(
                    getString(
                        R.string.sunrise_time,
                        sunrise.getFormattedTime()
                    )
                )
                tvSunset.setTextOrHide(getString(R.string.sunset_time, sunset.getFormattedTime()))
            }

            with(it.weather.first()) {
                tvWeather.setTextOrHide(description)
                ivWeather.setImage(getString(R.string.image_base_url).plus(icon).plus(".png"))
            }
        }
    }

    private fun getTemperature(minTemp: Float): String? {
        return getString(
            R.string.temperature_in_centi,
            String.format("%.2f", minTemp - 273.1)
        )
    }
}