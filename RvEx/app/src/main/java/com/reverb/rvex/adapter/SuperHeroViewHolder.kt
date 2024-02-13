package com.reverb.rvex.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.reverb.rvex.SuperHero
import com.reverb.rvex.databinding.ItemSuperheroBinding

class SuperHeroViewHolder(view: View): ViewHolder(view){

    private  val binding = ItemSuperheroBinding.bind(view)

//    private val ivSuperHero = view.findViewById<ImageView>(R.id.ivSuperHero)
//    private val tvSuperHeroName = view.findViewById<TextView>(R.id.tvSuperHeroName)
//    private val tvRealName = view.findViewById<TextView>(R.id.tvRealName)
//    private val tvPublisher = view.findViewById<TextView>(R.id.tvPublisher)


    fun render(superHeroModel: SuperHero, onCLickListener: (SuperHero) -> Unit){
        binding.tvSuperHeroName.text = superHeroModel.superHero
        binding.tvRealName.text = superHeroModel.realName
        binding.tvPublisher.text = superHeroModel.publisher

        Glide.with(binding.ivSuperHero.context).load(superHeroModel.photo).into(binding.ivSuperHero)

        itemView.setOnClickListener {
            onCLickListener(superHeroModel)
        }


    }//RENDER

}