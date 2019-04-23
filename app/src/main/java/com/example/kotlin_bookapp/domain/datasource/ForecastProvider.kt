package com.example.kotlin_bookapp.domain.datasource

import com.example.kotlin_bookapp.data.db.ForecastDb
import com.example.kotlin_bookapp.data.server.ForecastServer
import com.example.kotlin_bookapp.domain.model.ForecastList
import com.example.kotlin_bookapp.extensions.firstResult

class ForecastProvider(private val sources:List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        const val DAY_IN_MILLIS = 1000*60*60*24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode:Long, days:Int):ForecastList = sources.firstResult{
        requestSource(it, days, zipCode)
    }

    private fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long):ForecastList?{
        val res = source.requestForecastByZipCode(zipCode,todayTimeSpan())
        return if(res!=null && res.size >=days)res else null
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

}