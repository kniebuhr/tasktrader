package com.tasktrader.presentation.scenes.newtask

sealed class NewTaskIntent {
    class Create(val name: String, val value: String): NewTaskIntent()
}