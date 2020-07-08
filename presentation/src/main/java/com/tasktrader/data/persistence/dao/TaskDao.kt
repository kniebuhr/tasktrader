package com.tasktrader.data.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.tasktrader.data.persistence.dao.base.BaseDao
import com.tasktrader.data.persistence.entity.TaskEntity
import com.tasktrader.data.persistence.entity.TaskEntity.Companion.TASK_COMPLETED
import com.tasktrader.data.persistence.entity.TaskEntity.Companion.TASK_ID
import com.tasktrader.data.persistence.entity.TaskEntity.Companion.TASK_TABLE

@Dao
abstract class TaskDao : BaseDao<TaskEntity> {

    /**
     * @return A list of [TaskEntity] of all tasks in the table
     */
    @Query("SELECT * FROM $TASK_TABLE")
    abstract suspend fun getAll(): List<TaskEntity>

    /**
     * Completes the task by the id
     * @param id The task id
     * @param isCompleted Whether the task is completed or not
     * @return The number of tasks updated. This value should always be `1`
     */
    @Query("UPDATE $TASK_TABLE SET $TASK_COMPLETED = :isCompleted WHERE $TASK_ID = :id")
    abstract suspend fun updateIsCompleted(id: Long, isCompleted: Boolean): Int

    /**
     * Returns the task by the id
     * @param id The task id
     * @return The [TaskEntity] object
     */
    @Query("SELECT * FROM $TASK_TABLE WHERE $TASK_ID = :id")
    abstract suspend fun get(id: Long): TaskEntity

}