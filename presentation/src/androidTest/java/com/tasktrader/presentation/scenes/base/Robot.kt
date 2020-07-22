package com.tasktrader.presentation.scenes.base

import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread

abstract class Robot<in Model>(private val view: BaseView<Model>? = null) {

    protected fun <T> render(currentClass: T, model: Model, func: T.() -> Unit) =
        apply {
            view?.let {
                runOnUiThread { it.render(model) }
                currentClass.func()
            }
        }
}