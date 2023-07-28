package com.example.holamundo.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.holamundo.R

class CategoriesAdapter (private val categories: List<TasksCategory>): RecyclerView.Adapter<CategoriesViewHolder>(){

    //el adapater permite mostrar las listas y pintarlas


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return CategoriesViewHolder(view)
    }


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {

        holder.render( categories[position] )
    }


    override fun getItemCount(): Int = categories.size//en lugar de return


}