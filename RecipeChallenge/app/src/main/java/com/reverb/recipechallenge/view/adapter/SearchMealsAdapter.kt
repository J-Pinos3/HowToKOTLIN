package com.reverb.recipechallenge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.reverb.recipechallenge.databinding.MealItemRvBinding
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.model.datamodel.MealResume
import com.reverb.recipechallenge.view.viewholder.SearchMealsViewHolder

class SearchMealsAdapter( private val onItemSelected:(Any)->Unit,
        private val onSaveMeal:(Any) -> Unit
    ): RecyclerView.Adapter<SearchMealsViewHolder>() {

    private val diffCallBack = object: DiffUtil.ItemCallback<Any>(){
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            var result: Boolean = false
            if (oldItem is Meal && newItem is Meal)
                result = oldItem.idMeal == newItem.idMeal

            if(oldItem is MealResume && newItem is MealResume)
                result =  oldItem.idMeal == newItem.idMeal

            return result
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            var result: Boolean = false
            if (oldItem is Meal && newItem is Meal)
                result = oldItem == newItem

            if(oldItem is MealResume && newItem is MealResume)
                result =  oldItem == newItem

            return result
        }

    }

    val differList = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMealsViewHolder {
        return SearchMealsViewHolder(
            MealItemRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: SearchMealsViewHolder, position: Int) {
        holder.render(differList.currentList[position], onItemSelected, onSaveMeal)
    }

    override fun getItemCount(): Int = differList.currentList.size

}