package com.tasktrader.presentation.scenes.newtask

import androidx.lifecycle.*
import com.tasktrader.domain.model.Task
import com.tasktrader.domain.usecases.CreateTask
import com.tasktrader.presentation.scenes.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewTaskViewModel(
    private val createTask: CreateTask,
    private val router: NewTaskRouter
) : BaseViewModel<NewTaskModel, NewTaskIntent>() {

    private val _model = MutableLiveData<NewTaskModel>()
    override val model: LiveData<NewTaskModel> = _model

    override fun handle(intent: NewTaskIntent) {
        when (intent) {
            is NewTaskIntent.Create -> handleCreate(intent.name, intent.value)
        }
    }

    private fun handleCreate(taskName: String, taskValue: String) {
        viewModelScope.launch {
            val completed = createTask.execute(Task.new(taskName, taskValue.toInt()))
            if (completed) {
                router.finish()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val createTask: CreateTask,
        private val router: NewTaskRouter
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NewTaskViewModel(
                createTask = createTask,
                router = router
            ) as T
        }
    }
}