package com.tasktrader.domain.logger

import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Logger @Inject constructor() {

    private val tag = "LOGGER"

    fun d(block: () -> String) {
        if (Timber.treeCount() > 0) {
            try {
                Timber.d("$tag ${block()}")
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun i(block: () -> String) {
        if (Timber.treeCount() > 0) {
            try {
                Timber.i("$tag ${block()}")
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}