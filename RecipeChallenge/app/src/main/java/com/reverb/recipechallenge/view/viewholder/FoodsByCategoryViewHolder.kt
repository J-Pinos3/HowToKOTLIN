package com.reverb.recipechallenge.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.reverb.recipechallenge.databinding.MealItemRvBinding
import com.reverb.recipechallenge.model.datamodel.MealResume

class FoodsByCategoryViewHolder(private val binding: MealItemRvBinding): RecyclerView.ViewHolder(binding.root) {

    fun render(
        mealResume: MealResume,
        onMealSelected: (MealResume) -> Unit,
        onSaveMeal: (MealResume) -> Unit
    ) {
        binding.apply {
            Glide.with(ivMealImage.context).load(mealResume.strMealThumb)
                .into(ivMealImage)

            tvMealTimePrep.text = mealResume.idMeal.substring(0,2) + " min"
            tvMealName.text = mealResume.strMeal

            tvSaveMeal.setOnClickListener {
                onSaveMeal(mealResume)
            }
        }

        itemView.setOnClickListener {
            Snackbar.make(binding.root, "second recycler", Snackbar.LENGTH_LONG).show()
            onMealSelected(mealResume)
        }
    }
}