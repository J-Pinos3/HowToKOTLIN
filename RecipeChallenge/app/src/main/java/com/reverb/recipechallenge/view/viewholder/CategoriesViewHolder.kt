package com.reverb.recipechallenge.view.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.reverb.recipechallenge.R
import com.reverb.recipechallenge.databinding.CategoryItemRvBinding
import com.reverb.recipechallenge.model.datamodel.Category

class CategoriesViewHolder(private val binding: CategoryItemRvBinding)
    :RecyclerView.ViewHolder(binding.root){


    fun render(  category: Category, onCategorySelected: (Int)->Unit  ){
        binding.tvCategoryName.text = category.strCategory

        val colorBg = if(category.isSelected){
            R.color.purple_dark
        }else{
            R.color.purple_light
        }

        val colorText = if(category.isSelected){
            R.color.white
        }else{
            R.color.purple_dark
        }


        binding.tvCategoryName.apply {
            setBackgroundColor(ContextCompat.getColor(this.context, colorBg ))
            setTextColor(ContextCompat.getColor(this.context, colorText))
        }



        itemView.setOnClickListener {
            onCategorySelected(layoutPosition)
        }
    }


}