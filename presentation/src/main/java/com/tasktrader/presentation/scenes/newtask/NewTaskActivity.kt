package com.tasktrader.presentation.scenes.newtask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.tasktrader.R
import com.tasktrader.databinding.ActivityNewTaskBinding
import com.tasktrader.presentation.extensions.enableToolbar
import com.tasktrader.presentation.extensions.getText
import com.tasktrader.presentation.extensions.makeToast
import com.tasktrader.presentation.extensions.setVisible
import com.tasktrader.presentation.scenes.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewTaskActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context) = Intent(context, NewTaskActivity::class.java)
    }

    @Inject lateinit var viewModel: NewTaskViewModel

    private lateinit var binding: ActivityNewTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableToolbar(binding.toolbar, R.string.new_task_title, true)
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        binding.button.setOnClickListener {
            viewModel.handle(NewTaskIntent.Create(binding.name.getText(), binding.value.getText()))
        }
    }

    private fun setObservers() {
        viewModel.model.observe(this, Observer { model ->
            renderLoading(model.loading)
            renderErrorMessage(model.errorMessage)
        })
    }

    private fun renderLoading(loading: Boolean) {
        binding.loading.root.setVisible(loading)
    }

    private fun renderErrorMessage(message: String?) {
        message?.let {
            makeToast(it)
        }
    }
}