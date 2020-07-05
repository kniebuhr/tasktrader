package com.tasktrader.presentation.scenes.tasklist

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tasktrader.databinding.ActivityTaskListBinding
import com.tasktrader.domain.model.Task
import com.tasktrader.presentation.extensions.enableToolbar
import com.tasktrader.presentation.scenes.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskListActivity : BaseActivity() {

    @Inject lateinit var viewModel: TaskListViewModel
    @Inject lateinit var adapter: TaskListAdapter

    private lateinit var binding: ActivityTaskListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableToolbar(binding.toolbar, "Teste")
        setView()
        setObservers()
        viewModel.handle(TaskListIntent.LoadData)
    }

    private fun setView() {
        binding.recyclerView.apply {
            adapter = this@TaskListActivity.adapter
        }
    }

    private fun setObservers() {
        viewModel.model.observe(this, Observer { model ->
            renderData(model.data)
        })
    }

    private fun renderData(data: List<Task>?) {
        data?.let {
            adapter.items = it.toMutableList()
        }
    }
}
