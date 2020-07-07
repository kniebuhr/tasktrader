package com.tasktrader.presentation.scenes.tasklist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.tasktrader.R
import com.tasktrader.databinding.ActivityTaskListBinding
import com.tasktrader.domain.model.Task
import com.tasktrader.presentation.extensions.enableToolbar
import com.tasktrader.presentation.scenes.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskListActivity : BaseActivity() {

    companion object {
        const val MENU_ADD = 1
    }

    @Inject lateinit var viewModel: TaskListViewModel
    @Inject lateinit var adapter: TaskListAdapter

    private lateinit var binding: ActivityTaskListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableToolbar(binding.toolbar, R.string.task_list_title)
        setView()
        setObservers()
        viewModel.handle(TaskListIntent.LoadData)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(Menu.NONE, MENU_ADD, Menu.NONE, R.string.new_task_title)?.setIcon(R.drawable.ic_add)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_ADD -> viewModel.handle(TaskListIntent.NewTask)
        }
        return super.onOptionsItemSelected(item)
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
