package com.tasktrader.presentation.scenes.tasklist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasktrader.R
import com.tasktrader.databinding.ActivityTaskListBinding
import com.tasktrader.domain.model.Task
import com.tasktrader.presentation.extensions.enableToolbar
import com.tasktrader.presentation.extensions.makeToast
import com.tasktrader.presentation.extensions.setVisible
import com.tasktrader.presentation.scenes.base.BaseActivity
import com.tasktrader.presentation.scenes.base.BaseView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskListActivity : BaseView<TaskListModel>, BaseActivity() {

    companion object {
        const val MENU_ADD = 1

        fun newIntent(context: Context) = Intent(context, TaskListActivity::class.java)
    }

    @Inject lateinit var viewModel: TaskListViewModel
    @Inject lateinit var adapter: TaskListAdapter

    private lateinit var binding: ActivityTaskListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableToolbar(binding.toolbar, R.string.task_list_title)
        setAdapter()
        setObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.handle(TaskListIntent.LoadData)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(Menu.NONE, MENU_ADD, Menu.NONE, R.string.new_task_title)?.apply {
            setIcon(R.drawable.ic_add)
            setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_ADD -> viewModel.handle(TaskListIntent.NewTask)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setAdapter() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TaskListActivity, LinearLayoutManager.VERTICAL, false)
            adapter = this@TaskListActivity.adapter
        }

        adapter.listener = object : TaskListAdapter.Listener {
            override fun onItemCheckChanged(task: Task, position: Int) {
                viewModel.handle(TaskListIntent.CompleteTask(task, position))
            }
        }
    }

    private fun setObservers() {
        viewModel.model.observe(this, Observer { model ->
            render(model)
        })
    }

    override fun render(model: TaskListModel) {
        renderLoading(model.loading)
        renderErrorMessage(model.errorMessage)
        renderData(model.data)
        renderCompleteTask(model.completeTask, model.completeTaskPosition)
    }

    private fun renderLoading(loading: Boolean) {
        binding.loading.root.setVisible(loading)
    }

    private fun renderErrorMessage(message: String?) {
        message?.let {
            makeToast(it)
        }
    }

    private fun renderData(data: List<Task>?) {
        data?.let {
            adapter.items = it.toMutableList()
        }
    }

    private fun renderCompleteTask(task: Task?, position: Int?) {
        if (task == null || position == null) return
        adapter.setItem(task, position)
    }
}
