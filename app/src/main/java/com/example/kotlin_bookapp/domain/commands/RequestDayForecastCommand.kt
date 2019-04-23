package com.example.kotlin_bookapp.domain.commands

import com.example.kotlin_bookapp.domain.datasource.ForecastProvider
import com.example.kotlin_bookapp.domain.model.Forecast

class RequestDayForecastCommand(val id: Long, private val forecastProvider: ForecastProvider = ForecastProvider()) :
    Command<Forecast> {

    override fun execute(): Forecast = forecastProvider.requestForecast(id)
}