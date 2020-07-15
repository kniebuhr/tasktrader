package com.tasktrader.domain.usecases

import com.tasktrader.domain.logger.Logger
import com.tasktrader.domain.model.Task
import com.tasktrader.domain.repository.TaskRepository
import com.tasktrader.domain.usecases.base.Result
import com.tasktrader.domain.usecases.base.ResultUseCase
import javax.inject.Inject

class GetTaskList @Inject constructor(
    private val repository: TaskRepository,
    logger: Logger? = null
) : ResultUseCase<List<Task>, Unit>(logger) {

    override suspend fun build(param: Unit): Result<List<Task>> {
        return mapToResult { repository.getListTask() }
    }
}