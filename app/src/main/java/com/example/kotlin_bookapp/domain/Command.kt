package com.example.kotlin_bookapp.domain

interface Command<out T> {
    fun execute() : T
}