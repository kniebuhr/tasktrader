package com.tasktrader.presentation.scenes.tasklist

import com.tasktrader.domain.model.Task
import com.tasktrader.presentation.scenes.base.BaseView
import com.tasktrader.presentation.scenes.base.Robot
import com.tasktrader.R.id.*
import com.tasktrader.presentation.extensions.checkAdapterItemContent
import com.tasktrader.presentation.extensions.checkIsDisplayed

class TaskListRobot(view: BaseView<TaskListModel>? = null) : Robot<TaskListModel>(view) {

    companion object {
        fun robot(func: TaskListRobot.() -> Unit, view: BaseView<TaskListModel>) {
            TaskListRobot(view).func()
        }
    }

    //region MODEL

    fun loading(func: TaskListRobot.() -> Unit) =
        render(this, TaskListModel.createLoading(), func)

    fun error(message: String?, func: TaskListRobot.() -> Unit) =
        render(this, TaskListModel.createError(message), func)

    fun data(items: List<Task>, func: TaskListRobot.() -> Unit) =
        render(this, TaskListModel.createData(items), func)

    fun completeTask(task: Task, position: Int, func: TaskListRobot.() -> Unit) =
        render(this, TaskListModel.createCompleteTask(task, position), func)

    //endregion

    //region VIEWS

    fun loading(displayed: Boolean = true) {
        progress.checkIsDisplayed(displayed)
    }

    fun data(item: Task) {
        recyclerView.checkAdapterItemContent(0, item.name)
        recyclerView.checkAdapterItemContent(0, item.value.toString())
    }

    //endregion
}