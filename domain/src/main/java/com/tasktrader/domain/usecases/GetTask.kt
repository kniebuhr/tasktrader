package com.tasktrader.domain.usecases

import com.tasktrader.domain.logger.Logger
import com.tasktrader.domain.model.Task
import com.tasktrader.domain.repository.TaskRepository
import com.tasktrader.domain.usecases.base.Result
import com.tasktrader.domain.usecases.base.ResultUseCase
import javax.inject.Inject

class GetTask @Inject constructor(
    private val repository: TaskRepository,
    logger: Logger? = null
) : ResultUseCase<Task, Long>(logger) {

    override suspend fun build(param: Long): Result<Task> {
        return mapToResult { repository.getTask(param) }
    }
}