package com.tasktrader.presentation.scenes.newtask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.tasktrader.databinding.ActivityNewTaskBinding
import com.tasktrader.presentation.scenes.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewTaskActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context) = Intent(context, NewTaskActivity::class.java)
    }

    private lateinit var binding: ActivityNewTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}