package com.tasktrader.domain.usecases.base

import com.tasktrader.domain.logger.Logger


abstract class ResultUseCase<R, in Parameter>(private val logger: Logger) : UseCase<Result<R>, Parameter>(logger) {

    suspend fun mapToResult(block: suspend () -> R): Result<R> {
        return try {
            Result(block.invoke(), null)
        } catch (e: Exception) {
            Result(null, e)
        }
    }
}