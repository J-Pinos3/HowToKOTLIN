package com.reverb.recipechallenge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.reverb.recipechallenge.databinding.SavedItemRvBinding
import com.reverb.recipechallenge.model.datamodel.MealEntity
import com.reverb.recipechallenge.view.viewholder.FavoritesViewHolder

class FavoritesAdapter(
    private val onItemSelected:(MealEntity) -> Unit,
    private val onItemDeleted:(String, Int) -> Unit
): RecyclerView.Adapter<FavoritesViewHolder>()
{


    private val diffUtil = object: DiffUtil.ItemCallback<MealEntity>(){
        override fun areItemsTheSame(oldItem: MealEntity, newItem: MealEntity): Boolean {
            return oldItem.mealId == newItem.mealId
        }

        override fun areContentsTheSame(oldItem: MealEntity, newItem: MealEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differList = AsyncListDiffer(this, diffUtil)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            SavedItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.render(differList.currentList[position], onItemSelected, onItemDeleted)
    }

    override fun getItemCount(): Int = differList.currentList.size

}