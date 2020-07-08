package com.tasktrader.presentation.scenes.tasklist

import com.tasktrader.domain.model.Task

data class TaskListModel(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val snackMessage: String? = null,
    val data: List<Task>? = null,
    val completeTask: Task? = null,
    val completeTaskPosition: Int? = null
) {

    companion object {
        fun createData(data: List<Task>) =
            TaskListModel(data = data)

        fun createCompleteTask(task: Task, position: Int) =
            TaskListModel(completeTask = task,
                completeTaskPosition = position)

        fun createError(message: String?) =
            TaskListModel(errorMessage = message)
    }
}