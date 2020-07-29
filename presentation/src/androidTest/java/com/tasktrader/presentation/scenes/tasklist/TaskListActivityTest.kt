package com.tasktrader.presentation.scenes.tasklist

import com.tasktrader.domain.model.Task
import com.tasktrader.presentation.extensions.activityTestRule
import com.tasktrader.presentation.extensions.robot
import org.junit.Rule
import org.junit.Test

class TaskListActivityTest {

    @get:Rule
    val activityTestRule = activityTestRule<TaskListActivity>()

    @Test
    fun showData() {
        val items = listOf(Task(0, "Teste", 10, false))
        activityTestRule.robot {
            data(items) {
                data(items[0])
            }
        }
    }

}