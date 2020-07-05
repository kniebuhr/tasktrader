package com.tasktrader.domain.repository

import com.tasktrader.domain.model.Task

interface TaskRepository {

    //region TASK LIST

    suspend fun getListTask(): List<Task>

    suspend fun sortListTask(list: List<Task>): List<Task>

    //endregion

    //region TASK

    suspend fun getTask(id: Long): Task

    suspend fun completeTask(task: Task): Boolean

    //endregion
}