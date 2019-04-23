package com.example.kotlin_bookapp.domain.datasource

import com.example.kotlin_bookapp.domain.model.Forecast
import com.example.kotlin_bookapp.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode:Long, date:Long): ForecastList?
    fun requestDayForecast(id: Long): Forecast?
}