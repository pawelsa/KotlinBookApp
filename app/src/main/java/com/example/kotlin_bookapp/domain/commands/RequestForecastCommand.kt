package com.example.kotlin_bookapp.domain.commands

import com.example.kotlin_bookapp.domain.datasource.ForecastProvider
import com.example.kotlin_bookapp.domain.model.ForecastList

class RequestForecastCommand(
    private val zipCode: Long,
    private val forecastProvider: ForecastProvider = ForecastProvider()
) :
    Command<ForecastList> {

    companion object {
        const val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)

}