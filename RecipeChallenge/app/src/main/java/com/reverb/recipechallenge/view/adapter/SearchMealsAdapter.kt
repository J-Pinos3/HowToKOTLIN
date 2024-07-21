package com.reverb.recipechallenge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.reverb.recipechallenge.databinding.SearchMealItemRvBinding
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.view.viewholder.SearchMealsViewHolder

class SearchMealsAdapter( private val onItemSelected:(Meal)->Unit ): RecyclerView.Adapter<SearchMealsViewHolder>() {

    private val diffCallBack = object: DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return  oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }

    }

    val differList = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMealsViewHolder {
        return SearchMealsViewHolder(
            SearchMealItemRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: SearchMealsViewHolder, position: Int) {
        holder.render(differList.currentList[position], onItemSelected)
    }

    override fun getItemCount(): Int = differList.currentList.size

}