package com.reverb.rvex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reverb.rvex.R
import com.reverb.rvex.SuperHero

class SuperHeroAdapter(private val superHeroList: List<SuperHero>, private val onCLickListener: (SuperHero) -> Unit)
    :RecyclerView.Adapter<SuperHeroViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder( layoutInflater.inflate(R.layout.item_superhero, parent, false) )
    }


    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superHeroList[position]
        holder.render(item, onCLickListener)
    }


    override fun getItemCount(): Int = superHeroList.size


}