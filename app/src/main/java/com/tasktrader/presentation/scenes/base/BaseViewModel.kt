package com.tasktrader.presentation.scenes.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<Model, in Intent> : ViewModel() {
    abstract val model: LiveData<Model>
    abstract fun handle(intent: Intent)
}