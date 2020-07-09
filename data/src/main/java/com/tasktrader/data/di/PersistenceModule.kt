package com.tasktrader.data.di

import android.content.Context
import android.content.SharedPreferences
import com.tasktrader.data.logger.TimberLogger
import com.tasktrader.data.persistence.AppDatabase
import com.tasktrader.data.persistence.DatabaseFactory
import com.tasktrader.data.persistence.dao.TaskDao
import com.tasktrader.domain.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun providesLogger(): Logger {
        return TimberLogger()
    }

    @Provides
    @IODispatcher
    fun providesDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }


    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return DatabaseFactory.getDatabase(context)
    }

    @Provides
    @Singleton
    fun providesTaskDao(appDatabase: AppDatabase): TaskDao {
        return appDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)
    }
}