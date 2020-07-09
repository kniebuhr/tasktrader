package com.tasktrader.data.persistence

import android.content.Context
import androidx.room.Room

object DatabaseFactory {

    fun getDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "App Database")
            .fallbackToDestructiveMigration()
            .build()
}