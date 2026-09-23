package com.reverb.simpledictionarymvvm.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reverb.simpledictionarymvvm.model.DictionaryData
import com.reverb.simpledictionarymvvm.model.RetrofitInstance
import kotlinx.coroutines.launch

class DictionaryViewModel: ViewModel() {
    var isLoading by mutableStateOf(false)
        private set

    var wordData by mutableStateOf<DictionaryData?>(null)
        private set

    fun searchWord(word: String){
        if(word.isNullOrBlank()){  return     }

        viewModelScope.launch {
            isLoading = true //api fetched
            try {
                val response = RetrofitInstance.api.getWordMeaning(word)
                wordData = response.firstOrNull()
            }catch (e: Exception){
                wordData = null
            }
            isLoading = false
        }
    }

}