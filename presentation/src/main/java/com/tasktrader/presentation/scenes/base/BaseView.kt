package com.tasktrader.presentation.scenes.base

interface BaseView<in Model> {
    fun render(model: Model)
}