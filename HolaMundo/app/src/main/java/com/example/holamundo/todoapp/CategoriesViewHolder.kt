package com.example.holamundo.todoapp

import android.content.Context
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.holamundo.R

class CategoriesViewHolder(view:View) : RecyclerView.ViewHolder(view){

    private val tv_CategoryName: TextView = view.findViewById(R.id.tv_CategoryName)
    private val divider: View = view.findViewById(R.id.divider)


    fun render(tasksCategory: TasksCategory){


         when(tasksCategory){

             TasksCategory.Business -> {
                tv_CategoryName.text = "Negocios"
                 divider.setBackgroundColor(
                     ContextCompat.getColor(divider.context, R.color.business_category)
                 )
            }

            TasksCategory.Other -> {
                tv_CategoryName.text = "Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.others_category)
                )
            }

            TasksCategory.Personal -> {
                tv_CategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.personal_category)
                )
            }

        }//fin del when


    }


}//FIN DE CATEGORIESVIEWHOLDER