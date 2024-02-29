package com.reverb.secondmvvmexample.network.response

import com.google.gson.annotations.SerializedName
import com.reverb.secondmvvmexample.models.PeliculaModel

data class PeliculasResponse(
    @SerializedName("results")
    var resultados: List<PeliculaModel>
)
