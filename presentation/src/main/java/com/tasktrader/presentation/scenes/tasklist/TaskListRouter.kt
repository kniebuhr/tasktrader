package com.tasktrader.presentation.scenes.tasklist

import androidx.fragment.app.FragmentActivity
import com.tasktrader.presentation.scenes.newtask.NewTaskActivity
import javax.inject.Inject

class TaskListRouter @Inject constructor(
    private val activity: FragmentActivity
) {

    fun routeToNewTask() {
        activity.startActivity(NewTaskActivity.createIntent(activity))
    }
}