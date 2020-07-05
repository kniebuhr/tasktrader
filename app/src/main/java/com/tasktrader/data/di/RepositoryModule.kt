package com.tasktrader.data.di

import com.tasktrader.data.repository.TaskDataRepository
import com.tasktrader.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun providesTaskRepository(repo: TaskDataRepository): TaskRepository
}