package com.tasktrader.presentation.scenes.tasklist

import android.os.Bundle
import com.tasktrader.databinding.ActivityTaskListBinding
import com.tasktrader.presentation.scenes.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListActivity : BaseActivity() {

    private lateinit var binding: ActivityTaskListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
