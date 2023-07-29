package com.example.holamundo.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.holamundo.R


                                                // función lambda
class TasksAdapter (var tasks: List<Task>, private val onTaskSelected: (Int) -> Unit ) : RecyclerView.Adapter<TasksViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_task, parent, false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.render(tasks[position])
        //itemView es la vista
        holder.itemView.setOnClickListener { onTaskSelected(position) }

    }

    override fun getItemCount(): Int = tasks.size

}