package com.tasktrader.presentation.scenes.tasklist

import com.tasktrader.domain.model.Task

sealed class TaskListIntent {
    object LoadData : TaskListIntent()
    class CompleteTask(val task: Task, val position: Int) : TaskListIntent()
}