package com.tasktrader.presentation.scenes.tasklist

import androidx.lifecycle.*
import com.tasktrader.domain.model.Task
import com.tasktrader.domain.usecases.CompleteTask
import com.tasktrader.domain.usecases.GetTask
import com.tasktrader.domain.usecases.GetTaskList
import com.tasktrader.presentation.scenes.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskListViewModel(
    private val completeTask: CompleteTask,
    private val getTaskList: GetTaskList,
    private val getTask: GetTask,
    private val router: TaskListRouter
) : BaseViewModel<TaskListModel, TaskListIntent>() {

    private val _model = MutableLiveData<TaskListModel>()
    override val model: LiveData<TaskListModel> = _model

    override fun handle(intent: TaskListIntent) {
        when (intent) {
            TaskListIntent.LoadData -> handleLoadData()
            is TaskListIntent.CompleteTask -> handleCompleteTask(intent.task, intent.position)
            TaskListIntent.NewTask -> handleNewTask()
        }
    }

    private fun handleLoadData() {
        viewModelScope.launch {
            val result = getTaskList.execute(Any())
            _model.value = if (result.isSuccessful) {
                TaskListModel.createData(result.getResult())
            } else {
                TaskListModel.createError(result.getException().message)
            }
        }
    }

    private fun handleCompleteTask(task: Task, position: Int) {
        viewModelScope.launch {
            val result = completeTask.execute(task).run { getTask.execute(task.id) }
            _model.value = if (result.isSuccessful) {
                TaskListModel.createCompleteTask(result.getResult(), position)
            } else {
                TaskListModel.createError(result.getException().message)
            }
        }
    }

    private fun handleNewTask() {
        router.routeToNewTask()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val completeTask: CompleteTask,
        private val getTaskList: GetTaskList,
        private val getTask: GetTask,
        private val router: TaskListRouter
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TaskListViewModel(
                completeTask = completeTask,
                getTaskList = getTaskList,
                getTask = getTask,
                router = router
            ) as T
        }
    }
}