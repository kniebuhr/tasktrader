package com.tasktrader.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tasktrader.data.persistence.entity.TaskEntity.Companion.TASK_TABLE

@Entity(tableName = TASK_TABLE)
class TaskEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = TASK_ID) val id: Long = 0,
    @ColumnInfo(name = TASK_NAME) val name: String,
    @ColumnInfo(name = TASK_VALUE) val value: Int,
    @ColumnInfo(name = TASK_COMPLETED) val completed: Boolean
) {

    companion object {
        const val TASK_TABLE = "task"

        const val TASK_ID = "id"
        const val TASK_NAME = "name"
        const val TASK_VALUE = "value"
        const val TASK_COMPLETED = "completed"
    }
}