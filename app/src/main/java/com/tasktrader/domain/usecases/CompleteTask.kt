package com.tasktrader.domain.usecases

import com.tasktrader.domain.logger.Logger
import com.tasktrader.domain.model.Task
import com.tasktrader.domain.repository.TaskRepository
import com.tasktrader.domain.usecases.base.UseCase
import javax.inject.Inject

class CompleteTask @Inject constructor(
    private val repository: TaskRepository,
    logger: Logger
) : UseCase<Boolean, Task>(logger) {

    override suspend fun build(param: Task): Boolean {
        return repository.completeTask(param)
    }
}