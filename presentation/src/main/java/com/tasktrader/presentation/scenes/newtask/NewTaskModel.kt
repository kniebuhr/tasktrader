package com.tasktrader.presentation.scenes.newtask

data class NewTaskModel(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val snackMessage: String? = null
) {

    companion object {

        fun createLoading() =
            NewTaskModel(loading = true)

        fun createError(message: String?) =
            NewTaskModel(errorMessage = message)
    }
}