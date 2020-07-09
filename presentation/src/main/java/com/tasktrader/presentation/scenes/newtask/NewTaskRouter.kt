package com.tasktrader.presentation.scenes.newtask

import androidx.fragment.app.FragmentActivity
import com.tasktrader.R
import com.tasktrader.presentation.extensions.makeToast
import javax.inject.Inject

class NewTaskRouter @Inject constructor(
    private val activity: FragmentActivity
) {

    fun finish() {
        activity.baseContext.makeToast(activity.getString(R.string.new_task_saved))
        activity.finish()
    }
}