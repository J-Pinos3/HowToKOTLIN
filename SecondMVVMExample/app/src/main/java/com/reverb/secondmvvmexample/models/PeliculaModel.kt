package com.reverb.secondmvvmexample.models

import com.google.gson.annotations.SerializedName

data class PeliculaModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("original_title")
    val nombrePelicula: String,
    @SerializedName("overview")
    val descripcion: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("release_date")
    val fechaLanzamiento: String,
    @SerializedName("vote_average")
    val votoPromedio: String,
    @SerializedName("vote_count")
    val totalVotos: String
)