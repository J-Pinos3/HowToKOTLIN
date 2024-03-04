package com.reverb.simpsonsquotesmvvm.views

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reverb.simpsonsquotesmvvm.R
import com.reverb.simpsonsquotesmvvm.models.Personaje

class PersonajeAdapter(val context: Context, var personajesList: List<Personaje>)
    : RecyclerView.Adapter<PersonajeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return PersonajeViewHolder( layoutInflater.inflate(R.layout.item_rv_personajes, parent, false) )
    }


    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val item = personajesList[position]
        holder.render(item)
    }


    override fun getItemCount(): Int = personajesList.size


}