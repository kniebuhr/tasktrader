package com.tasktrader.domain.usecases.base

class Result<out Result>(
    private val result: Result?,
    private val throwable: Throwable?
) {

    val isSuccessful = result != null && throwable == null
    val isFailure = throwable != null

    fun getResult(): Result {
        return result ?: throw Throwable("Calling getResult on unsuccessful result")
    }

    fun getException(): Throwable {
        return throwable ?: throw Throwable("Calling getException on successful result")
    }
}