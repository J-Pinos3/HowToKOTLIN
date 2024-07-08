package com.reverb.recipechallenge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.reverb.recipechallenge.databinding.CategoryItemRvBinding
import com.reverb.recipechallenge.model.datamodel.Category
import com.reverb.recipechallenge.view.viewholder.CategoriesViewHolder

class CategoriesAdapter(private val onItemSelected: (Int)->Unit):
    RecyclerView.Adapter<CategoriesViewHolder>() {

    private val diffCallback = object: DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    var differList = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            CategoryItemRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(differList.currentList[position], onItemSelected)
    }

    override fun getItemCount(): Int = differList.currentList.size

}