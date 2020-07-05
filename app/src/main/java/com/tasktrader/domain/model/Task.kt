package com.tasktrader.domain.model

data class Task(
    val id: Long,
    val name: String,
    val completed: Boolean
)