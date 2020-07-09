package com.tasktrader.presentation.scenes.newtask

import androidx.fragment.app.FragmentActivity
import com.tasktrader.presentation.extensions.makeToast
import javax.inject.Inject

class NewTaskRouter @Inject constructor(
    private val activity: FragmentActivity
) {

    fun finish() {
        // TODO - passar pra resources
        activity.baseContext.makeToast("Task salva com sucesso")
        activity.finish()
    }
}