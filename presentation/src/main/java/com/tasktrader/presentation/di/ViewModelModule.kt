package com.tasktrader.presentation.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.tasktrader.presentation.scenes.newtask.NewTaskViewModel
import com.tasktrader.presentation.scenes.tasklist.TaskListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {

    @Provides
    @ActivityScoped
    fun provideNewTaskViewModel(activity: FragmentActivity, factory: NewTaskViewModel.Factory): NewTaskViewModel {
        return ViewModelProvider(activity, factory).get(NewTaskViewModel::class.java)
    }

    @Provides
    @ActivityScoped
    fun provideTaskListViewModel(activity: FragmentActivity, factory: TaskListViewModel.Factory): TaskListViewModel {
        return ViewModelProvider(activity, factory).get(TaskListViewModel::class.java)
    }

}