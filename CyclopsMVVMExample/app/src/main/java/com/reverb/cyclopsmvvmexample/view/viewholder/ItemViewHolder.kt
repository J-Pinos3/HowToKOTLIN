package com.reverb.cyclopsmvvmexample.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.reverb.cyclopsmvvmexample.databinding.PokemonRowBinding
import com.reverb.cyclopsmvvmexample.model.PokemonDataModel

class ItemViewHolder(binding: PokemonRowBinding): ViewHolder(binding.root) {
    private var binding: PokemonRowBinding? = null

    init {
        this.binding = binding
    }

    fun setItem(model: PokemonDataModel){
        binding?.let {view->
            view.name = model.name

            Glide.with(view.root.context).load(model.img).into(view.imgCharacter)

        }
    }

}