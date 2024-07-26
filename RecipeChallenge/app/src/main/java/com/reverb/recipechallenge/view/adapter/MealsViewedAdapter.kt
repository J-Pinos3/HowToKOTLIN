package com.reverb.recipechallenge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.reverb.recipechallenge.databinding.MealItemRvBinding
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.view.viewholder.MealsViewedViewHolder

class MealsViewedAdapter(private val onItemSelected:(Meal)->Unit,
        private val onSaveMeal:(Meal)->Unit
    ): RecyclerView.Adapter<MealsViewedViewHolder>() {

    private val diffCallback = object: DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }

    }

    val differList = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewedViewHolder {
        return MealsViewedViewHolder(
            MealItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MealsViewedViewHolder, position: Int) {
        holder.render(differList.currentList[position], onItemSelected, onSaveMeal)
    }

    override fun getItemCount(): Int = differList.currentList.size

}