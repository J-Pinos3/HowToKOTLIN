package com.reverb.recipechallenge.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.reverb.recipechallenge.databinding.MealItemRvBinding
import com.reverb.recipechallenge.model.datamodel.Meal

class MealsViewedViewHolder(private val binding: MealItemRvBinding): RecyclerView.ViewHolder(binding.root) {

    fun render(meal: Meal, onMealSelected:(Meal) -> Unit){
        binding.apply {
            Glide.with(ivMealImage.context).load(meal.strMealThumb)
                .into(ivMealImage)

            tvMealTimePrep.text = meal.idMeal.substring(0,2) + " min"
            tvMealName.text = meal.strMeal
        }

        itemView.setOnClickListener {
            Snackbar.make(binding.root, "first recycler", Snackbar.LENGTH_LONG).show()
            onMealSelected(meal)
        }
    }



}