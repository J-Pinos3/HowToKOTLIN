package com.reverb.recipechallenge.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.reverb.recipechallenge.databinding.SearchMealItemRvBinding
import com.reverb.recipechallenge.model.datamodel.Meal

class SearchMealsViewHolder(private val binding: SearchMealItemRvBinding): RecyclerView.ViewHolder(binding.root) {

    fun render(meal: Meal, onItemSelected: (Meal) -> Unit){
        binding.apply {
            Glide.with(ivSearchMeal.context).load(meal.strMealThumb).into(ivSearchMeal)
            tvSearchMealName.text = meal.strMeal
            tvSearchMealCategory.text = meal.strTags.toString()
        }

        itemView.setOnClickListener {
            Snackbar.make(binding.root, "search recycler", Snackbar.LENGTH_LONG).show()
            onItemSelected(meal)
        }
    }


}