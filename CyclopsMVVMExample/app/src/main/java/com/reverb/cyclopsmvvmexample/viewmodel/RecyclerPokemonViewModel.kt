package com.reverb.cyclopsmvvmexample.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reverb.cyclopsmvvmexample.model.PokemonDataModel
import com.reverb.cyclopsmvvmexample.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecyclerPokemonViewModel(app: Application): AndroidViewModel(app), CoroutineScope {

    private val _itemSelected = MutableLiveData<PokemonDataModel?>()
    var itemDataSelected: PokemonDataModel? = null

    private val _listState = MutableLiveData< MutableList<PokemonDataModel>? >()
    var listState: LiveData<MutableList<PokemonDataModel>?> = _listState

    private val _progressState = MutableLiveData< Boolean >()
    val progressState: LiveData< Boolean > = _progressState

    private val repository = PokemonRepository()
    lateinit var observerOnCategorySelected: Observer<PokemonDataModel>

    private val viewModelJob = Job()

    override val coroutineContext: CoroutineContext
        get() = viewModelJob + Dispatchers.Default


    init {
        initObserver()

    }

    private fun initObserver() {
        observerOnCategorySelected = Observer {value ->
            value.let {
                _itemSelected.value = it
            }
        }
    }



    fun clearSelection(){
        _itemSelected.value = null
    }

    fun setItemSelection(item: PokemonDataModel){
        itemDataSelected = item
    }

    fun fetchPokemonData(){
        _progressState.value = true
        viewModelScope.launch {
            val response = repository.getPokemon()
            response?.body()?.pokemon.let {list->
                _listState.value = list
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}