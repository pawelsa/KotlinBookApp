package com.example.kotlin_bookapp.domain.datasource

import com.example.kotlin_bookapp.data.db.ForecastDb
import com.example.kotlin_bookapp.data.server.ForecastServer
import com.example.kotlin_bookapp.domain.model.Forecast
import com.example.kotlin_bookapp.domain.model.ForecastList
import com.example.kotlin_bookapp.extensions.firstResult

class ForecastProvider(private val sources: List<ForecastDataSource> = SOURCES) {

    companion object {
        const val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES by lazy { listOf(ForecastDb(), ForecastServer()) }
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSource {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }

    private fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
        val res = source.requestForecastByZipCode(zipCode,todayTimeSpan())
        return if(res!=null && res.size >=days)res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSource { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun <T : Any> requestToSource(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }

}