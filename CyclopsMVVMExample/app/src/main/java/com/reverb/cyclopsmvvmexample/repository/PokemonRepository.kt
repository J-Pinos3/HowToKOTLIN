package com.reverb.cyclopsmvvmexample.repository

import com.reverb.cyclopsmvvmexample.services.RetrofitClient
import com.reverb.cyclopsmvvmexample.services.WebService

class PokemonRepository {
    private var apiService: WebService? = null

    init {
        apiService = RetrofitClient.getClient?.create(WebService::class.java)
    }

    suspend fun getPokemon() = apiService?.getPokemons()
}