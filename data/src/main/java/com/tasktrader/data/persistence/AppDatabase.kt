package com.tasktrader.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tasktrader.data.persistence.dao.TaskDao
import com.tasktrader.data.persistence.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

}