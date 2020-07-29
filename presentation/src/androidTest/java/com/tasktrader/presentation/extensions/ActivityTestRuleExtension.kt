package com.tasktrader.presentation.extensions

import android.app.Activity
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.tasktrader.presentation.scenes.tasklist.TaskListActivity
import com.tasktrader.presentation.scenes.tasklist.TaskListRobot

fun ActivityTestRule<TaskListActivity>.robot(func: TaskListRobot.() -> Unit) {
    val context = InstrumentationRegistry.getInstrumentation().targetContext
    val activity = launchActivity(TaskListActivity.newIntent(context))
    TaskListRobot.robot(func, activity)
}

inline fun <reified T : Activity> activityTestRule(
    initialTouchMode: Boolean = false,
    launchActivity: Boolean = false
): ActivityTestRule<T> =
    ActivityTestRule(T::class.java, initialTouchMode, launchActivity)