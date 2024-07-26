package com.reverb.recipechallenge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.reverb.recipechallenge.databinding.MealItemRvBinding
import com.reverb.recipechallenge.model.datamodel.MealResume
import com.reverb.recipechallenge.view.viewholder.FoodsByCategoryViewHolder

class FoodsByCategoryAdapter(private val onSelectedItem: (MealResume)-> Unit,
        private val onSaveMeal: (MealResume) -> Unit
    ):RecyclerView.Adapter<FoodsByCategoryViewHolder>()
{

    private val diffCallback = object: DiffUtil.ItemCallback<MealResume>(){
        override fun areItemsTheSame(oldItem: MealResume, newItem: MealResume): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: MealResume, newItem: MealResume): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsByCategoryViewHolder {
        return FoodsByCategoryViewHolder(
            MealItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FoodsByCategoryViewHolder, position: Int) {
        val mealResume = differ.currentList[position]
        holder.render(mealResume, onSelectedItem, onSaveMeal)
    }

    override fun getItemCount(): Int = differ.currentList.size


}