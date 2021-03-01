package com.mobile.mobiquityassignment.ui.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.mobiquityassignment.R
import com.mobile.mobiquityassignment.service.model.ForecastResponse
import com.mobile.mobiquityassignment.utils.setImage
import com.mobile.mobiquityassignment.utils.setTextOrHide
import kotlinx.android.synthetic.main.item_five_days_forecast.view.*

class FiveDaysForecastAdapter :
    RecyclerView.Adapter<FiveDaysForecastAdapter.FiveDaysForecastViewHolder>() {

    private val items = ArrayList<ForecastResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiveDaysForecastViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_five_days_forecast, parent, false)
        return FiveDaysForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: FiveDaysForecastViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class FiveDaysForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(forecastResp: ForecastResponse) {
            with(itemView) {
                forecastResp.run {
                    tvDate.setTextOrHide(date)
                    tvTemperature.setTextOrHide(
                        context.getString(
                            R.string.temperature_in_centi,
                            String.format("%.2f", main.temp)
                        )
                    )
                    ivFiveDaysForecast.setImage(
                        context.getString(R.string.image_base_url).plus(weather.first().icon)
                            .plus(".png")
                    )
                    tvWeather.setTextOrHide(weather.first().description)
                }
            }
        }
    }

    fun updateData(data: MutableList<ForecastResponse>) {
        items.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }
}