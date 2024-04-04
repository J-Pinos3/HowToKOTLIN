package com.reverb.cyclopsmvvmexample.model

data class PokemonResponse(
    val pokemon: MutableList<PokemonDataModel> = mutableListOf()

)