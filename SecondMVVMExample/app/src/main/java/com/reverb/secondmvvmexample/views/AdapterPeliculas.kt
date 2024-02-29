package com.reverb.secondmvvmexample.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.reverb.secondmvvmexample.R
import com.reverb.secondmvvmexample.core.Constantes
import com.reverb.secondmvvmexample.models.PeliculaModel

class AdapterPeliculas(val context:Context, var listaPeliculas: List<PeliculaModel> )
    :RecyclerView.Adapter<AdapterPeliculas.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cvPelicula = itemView.findViewById<CardView>(R.id.cvPelicula)
        val Poster = itemView.findViewById<ImageView>(R.id.Poster)
        val circular_progress = itemView.findViewById<CircularProgressIndicator>(R.id.circular_progress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_pelicula, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = listaPeliculas[position]
        Glide.with(context).load("${Constantes.BASE_URL_IMAGENES}${pelicula.poster}")
            .apply(RequestOptions().override(Constantes.IMAGEN_ANCHO, Constantes.IMAGE_ALTO))
            .into(holder.Poster)

        holder.circular_progress.maxProgress = Constantes.MAX_CALIFICACION
        holder.circular_progress.setCurrentProgress(pelicula.votoPromedio.toDouble())
    }

    override fun getItemCount() = listaPeliculas.size

}