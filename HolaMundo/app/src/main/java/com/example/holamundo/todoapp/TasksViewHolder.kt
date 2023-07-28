package com.example.holamundo.todoapp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.TextureView
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.holamundo.R

class TasksViewHolder (view: View) : RecyclerView.ViewHolder(view){

    private val tvTask: TextView = view.findViewById(R.id.tvTask)
    private val checkBoxTask: CheckBox = view.findViewById(R.id.checkBoxTask)

    fun render(task: Task){

        if(task.isSelected){
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvTask.paintFlags = tvTask.paintFlags and  Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        tvTask.text = task.name
        checkBoxTask.isChecked = task.isSelected

        val color = when(task.category){
            TasksCategory.Business -> R.color.business_category
            TasksCategory.Other -> R.color.others_category
            TasksCategory.Personal -> R.color.personal_category
        }
        checkBoxTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(checkBoxTask.context, color)
        )

    }
}