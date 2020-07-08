package com.tasktrader.domain.logger

interface Logger {
    fun d(block: () -> String)
    fun i(block: () -> String)
}