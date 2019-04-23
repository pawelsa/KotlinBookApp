package com.example.kotlin_bookapp.domain.commands

interface Command<out T> {
    fun execute() : T
}