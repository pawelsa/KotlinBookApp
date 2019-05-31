package com.example.kotlin_bookapp.domain.commands

import com.example.kotlin_bookapp.domain.datasource.ForecastProvider
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RequestDayForecastCommandTest{

    @Test
    fun `provider is called when command is executed`(){
        val forecastProvider = mock(ForecastProvider::class.java)
        val command = RequestDayForecastCommand(2, forecastProvider)

        command.execute()

        verify(forecastProvider).requestForecast(2)
    }

}