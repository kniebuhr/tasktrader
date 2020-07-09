package com.tasktrader.data.repository

import com.tasktrader.data.di.IODispatcher
import com.tasktrader.data.mapper.TaskMapper
import com.tasktrader.data.persistence.entity.TaskEntity
import com.tasktrader.data.persistence.processor.TaskProcessor
import com.tasktrader.domain.logger.Logger
import com.tasktrader.domain.model.Task
import com.tasktrader.domain.repository.TaskRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * [TaskRepository] to get data
 */
class TaskDataRepository @Inject constructor(
    private val taskProcessor: TaskProcessor,
    private val taskMapper: TaskMapper,
    private val logger: Logger,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : TaskRepository {

    //region TASK LIST

    override suspend fun getListTask(): List<Task> {
        logger.i { "getListTask invoked" }
        return withContext(dispatcher) {
            taskProcessor.getAll().map { taskMapper.transformToModel(it) }
        }
    }

    override suspend fun sortListTask(list: List<Task>): List<Task> {
        logger.i { "sortListTask invoked" }
        return list.sortedBy { it.id }
    }

    //endregion

    //region TASK

    override suspend fun getTask(id: Long): Task {
        logger.i { "getTask invoked for task with id $id" }
        return withContext(dispatcher) {
            taskProcessor.get(id).let { taskMapper.transformToModel(it) }
        }
    }

    override suspend fun completeTask(task: Task): Boolean {
        logger.i { "completeTask invoked for task ${task.name}" }
        return withContext(dispatcher) {
            taskProcessor.update(task.id, !task.completed)
        }
    }

    override suspend fun createTask(task: Task): Boolean {
        logger.i { "createTask invoked for task ${task.name}" }
        return withContext(dispatcher) {
            taskProcessor.insert(taskMapper.transformToEntity(task.copy()))
        }
    }

    //endregion
}