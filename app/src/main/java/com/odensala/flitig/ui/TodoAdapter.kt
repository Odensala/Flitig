package com.odensala.flitig.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.odensala.flitig.data.Todo
import com.odensala.flitig.databinding.TodoItemBinding

class TodoAdapter : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class TodoViewHolder(private val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.apply {
                checkboxCompleted.isChecked = todo.completed
                textviewTodoTitle.text = todo.title
                textviewTodoTitle.paint.isStrikeThruText = todo.completed
                imageviewImportance.isVisible = todo.important
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo) =
            oldItem == newItem
    }
}