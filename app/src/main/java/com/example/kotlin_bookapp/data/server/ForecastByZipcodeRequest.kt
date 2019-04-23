package com.example.kotlin_bookapp.data.server

import com.google.gson.Gson
import java.net.URL

class ForecastByZipcodeRequest(private val zipCode: Long) {

    companion object {
        private const val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private const val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private const val COMPLETE_URL = "$URL&APPID=$APP_ID&zip="
    }

    fun execute(): ForecastResult {
        val forecastJsonString = URL(COMPLETE_URL + zipCode).readText()
        return Gson().fromJson(forecastJsonString, ForecastResult::class.java)
    }
}