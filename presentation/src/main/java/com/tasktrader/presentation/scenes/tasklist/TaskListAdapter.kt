package com.tasktrader.presentation.scenes.tasklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tasktrader.databinding.ItemTaskBinding
import com.tasktrader.domain.model.Task
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class TaskListAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface Listener {
        fun onItemCheckChanged(task: Task, position: Int)
    }

    var listener: Listener? = null

    var items = mutableListOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setItem(item: Task, position: Int) {
        items[position] = item
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> holder.bind(items[position], position)
        }
    }

    private inner class ItemViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        private var isBinding = false
        fun bind(task: Task, position: Int) {
            binding.value.text = task.value.toString()
            binding.checkbox.text = task.name
            binding.checkbox.isChecked = task.completed
            binding.checkbox.setOnCheckedChangeListener { _, _ ->
                if (!isBinding) listener?.onItemCheckChanged(task, position)
            }
        }
    }
}