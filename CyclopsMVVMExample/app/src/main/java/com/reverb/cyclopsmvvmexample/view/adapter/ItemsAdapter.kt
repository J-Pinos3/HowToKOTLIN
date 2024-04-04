package com.reverb.cyclopsmvvmexample.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.reverb.cyclopsmvvmexample.R
import com.reverb.cyclopsmvvmexample.databinding.PokemonRowBinding
import com.reverb.cyclopsmvvmexample.model.PokemonDataModel
import com.reverb.cyclopsmvvmexample.view.fragments.ClickListener
import com.reverb.cyclopsmvvmexample.view.viewholder.ItemViewHolder

class ItemsAdapter(private val listener: ClickListener): RecyclerView.Adapter<ItemViewHolder>() {

    private val resource = R.layout.pokemon_row
    lateinit var context: Context
    private val itemList = mutableListOf<PokemonDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: PokemonRowBinding =
            DataBindingUtil.inflate(layoutInflater, resource, parent, false)

        return ItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setItem(itemList[position])

        holder.itemView.setOnClickListener {
            listener.itemSelected(itemList[position])
        }
    }


    override fun getItemCount(): Int = itemList.size


    fun setItems(list: MutableList<PokemonDataModel>){
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}