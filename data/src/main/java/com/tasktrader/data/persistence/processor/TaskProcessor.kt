package com.tasktrader.data.persistence.processor

import com.tasktrader.data.persistence.dao.TaskDao
import com.tasktrader.data.persistence.entity.TaskEntity
import com.tasktrader.data.persistence.processor.base.BaseProcessor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskProcessor @Inject constructor(private val dao: TaskDao) : BaseProcessor<TaskEntity>(dao) {

    suspend fun getAll(): List<TaskEntity> = dao.getAll()

    suspend fun update(id: Long, isCompleted: Boolean): Boolean = dao.updateIsCompleted(id, isCompleted) > 0

    suspend fun get(id: Long): TaskEntity = dao.get(id)
}