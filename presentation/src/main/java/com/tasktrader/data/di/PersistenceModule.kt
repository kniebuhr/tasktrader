package com.tasktrader.data.di

import android.content.Context
import android.content.SharedPreferences
import com.tasktrader.BuildConfig
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
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PersistenceModule {

    // TODO Atualizar isso para uma classe no módulo de data
    @Provides
    fun providesLogger(): Logger {
        return object : Logger {
            override fun d(block: () -> String) {
                if (Timber.treeCount() > 0) {
                    try {
                        Timber.d(block())
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                }
            }

            override fun i(block: () -> String) {
                if (Timber.treeCount() > 0) {
                    try {
                        Timber.i(block())
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                }
            }
        }
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
        return context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }
}