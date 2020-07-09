package com.tasktrader.data.logger

import com.tasktrader.domain.logger.Logger
import timber.log.Timber

class TimberLogger : Logger {
    override fun d(block: () -> String) {
        if (Timber.treeCount() > 0) {
            try {
                Timber.d(block())
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    override fun i(block: () -> String) {
        if (Timber.treeCount() > 0) {
            try {
                Timber.i(block())
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}