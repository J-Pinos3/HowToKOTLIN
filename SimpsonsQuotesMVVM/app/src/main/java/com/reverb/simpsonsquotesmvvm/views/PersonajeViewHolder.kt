package com.reverb.simpsonsquotesmvvm.views

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.reverb.simpsonsquotesmvvm.R
import com.reverb.simpsonsquotesmvvm.databinding.ItemRvPersonajesBinding
import com.reverb.simpsonsquotesmvvm.models.Personaje

class PersonajeViewHolder(view: View): ViewHolder(view) {

    private val binding = ItemRvPersonajesBinding.bind(view)


    fun render(personaje: Personaje){
        Glide.with(binding.ivPersonaje.context).load(personaje.image).into(binding.ivPersonaje)
        binding.tvNomPersonaje.text = personaje.character

        binding.cvPersonaje.setOnClickListener {
            mostrarFrase(personaje.quote)
        }
    }

    private fun mostrarFrase(frase: String){
        val bottomSheetDialog = BottomSheetDialog(itemView.context)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_frase)

        val tvFrase = bottomSheetDialog.findViewById<TextView>(R.id.tvFrase)
        tvFrase?.text = frase

        bottomSheetDialog.show()
    }
}