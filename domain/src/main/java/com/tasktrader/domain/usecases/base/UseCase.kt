package com.tasktrader.domain.usecases.base

import com.tasktrader.domain.logger.Logger

/**
 * Abstract class for a use case
 */
abstract class UseCase<out Result, in Parameter>(private val logger: Logger?) {

    protected abstract suspend fun build(param: Parameter): Result

    suspend fun execute(param: Parameter): Result = execute(param, false)

    protected open suspend fun execute(param: Parameter, fromUseCase: Boolean): Result {
        logger?.i { "Executing UseCase for param $param" }
        return build(param)
    }
}