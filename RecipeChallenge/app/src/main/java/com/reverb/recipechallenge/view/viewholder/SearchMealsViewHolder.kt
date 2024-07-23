package com.reverb.recipechallenge.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.reverb.recipechallenge.databinding.MealItemRvBinding
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.model.datamodel.MealResume

class SearchMealsViewHolder(private val binding: MealItemRvBinding): RecyclerView.ViewHolder(binding.root) {

    fun render(mealItem: Any, onItemSelected: (Any) -> Unit){
        binding.apply {

            if(mealItem is MealResume){
                Glide.with(ivMealImage.context).load(mealItem.strMealThumb).into(ivMealImage)
                tvMealName.text = mealItem.strMeal
                tvMealTimePrep.text = mealItem.idMeal.substring(0,2) + " min"
            }else if(mealItem is Meal){
                Glide.with(ivMealImage.context).load(mealItem.strMealThumb).into(ivMealImage)
                tvMealName.text = mealItem.strMeal
                tvMealTimePrep.text = mealItem.idMeal.substring(0,2) + " min"
            }

        }

        itemView.setOnClickListener {
            Snackbar.make(binding.root, "search recycler", Snackbar.LENGTH_LONG).show()
            onItemSelected(mealItem)
        }
    }


}