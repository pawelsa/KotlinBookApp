package com.example.kotlin_bookapp.data.server

import com.example.kotlin_bookapp.data.db.ForecastDb
import com.example.kotlin_bookapp.domain.datasource.ForecastDataSource
import com.example.kotlin_bookapp.domain.model.Forecast
import com.example.kotlin_bookapp.domain.model.ForecastList

class ForecastServer(private val dataMapper:ServerDataMapper = ServerDataMapper(), private val forecastDb:ForecastDb = ForecastDb()) :ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipcodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long): Forecast? = throw UnsupportedOperationException()
}