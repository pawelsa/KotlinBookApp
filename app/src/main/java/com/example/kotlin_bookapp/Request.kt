package com.example.kotlin_bookapp

import android.util.Log
import java.net.URL

class Request(private val url:String) {

    private val TAG = this::class.java.simpleName

    fun run(){
        val forecastJsonStr = URL(url).readText()
        Log.d(TAG, "run: $forecastJsonStr")
    }
}