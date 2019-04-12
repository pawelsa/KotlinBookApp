package com.example.kotlin_bookapp.domain.commands

import com.example.kotlin_bookapp.ForecastRequest
import com.example.kotlin_bookapp.domain.Command
import com.example.kotlin_bookapp.domain.ForecastList
import com.example.kotlin_bookapp.domain.mappers.ForecastDataMapper

class RequestForecastCommand(private val zipCode : String) : Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}