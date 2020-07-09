package com.tasktrader.domain.model

data class Task(
    val id: Long,
    val name: String,
    val value: Int,
    val completed: Boolean
) {

    companion object {
        fun new(name: String, value: Int): Task {
            return Task(id = 0,
                name = name,
                value = value,
                completed = false)
        }
    }
}