package com.example.kotlin_bookapp.domain.mappers

import com.example.kotlin_bookapp.Forecast
import com.example.kotlin_bookapp.ForecastResult
import com.example.kotlin_bookapp.domain.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import com.example.kotlin_bookapp.domain.Forecast as ModelForecast

class ForecastDataMapper {

    fun convertFromDataModel(forecastResult: ForecastResult): ForecastList =
        ForecastList(
            forecastResult.city.name,
            forecastResult.city.country,
            convertForecastToDomain(forecastResult.list)
        )

    private fun convertForecastToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(
            convertDate(forecast.dt),
            forecast.weather[0].description, forecast.temp.max.toInt(),
            forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon)
        )
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }
}