package com.reverb.recipechallenge.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reverb.recipechallenge.databinding.SavedItemRvBinding
import com.reverb.recipechallenge.model.datamodel.MealEntity

class FavoritesViewHolder(private val binding: SavedItemRvBinding): RecyclerView.ViewHolder(binding.root) {

    fun render(
        mealEntity: MealEntity,
        onMealSelected: (MealEntity) -> Unit,
        onItemDeleted: (MealEntity) -> Unit
    ){
        binding.apply {
            Glide.with(ivMealImage.context).load(mealEntity.mealImage).into(ivMealImage)

            tvMealTimePrep.text = mealEntity.mealId.substring(0,2) + " min"
            tvMealName.text = mealEntity.mealName

            tvDeleteMeal.setOnClickListener {
                onItemDeleted(mealEntity)
            }
        }

        itemView.setOnClickListener {
            //todo add goto recipe fragment
            onMealSelected(mealEntity)
        }
    }


}