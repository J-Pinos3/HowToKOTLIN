package com.reverb.mvvmex.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reverb.mvvmex.data.model.QuoteModel
import com.reverb.mvvmex.domain.GetQuotesUseCase
import com.reverb.mvvmex.domain.GetRandomQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel(){

    val quoteModel = MutableLiveData<QuoteModel>()
    val isloading = MutableLiveData<Boolean>()

    //var getQuotesUseCase = GetQuotesUseCase()
    //var getRandomQuoteUseCase = GetRandomQuoteUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isloading.postValue(true)
            val result = getQuotesUseCase()
            if( !result.isNullOrEmpty() ){
                quoteModel.postValue(result[0])
                isloading.postValue(false)
            }
        }
    }


    //le da una cita random a la activity con un setonclicListener
    fun randomQuote(){
        isloading.postValue(true)
        val quote = getRandomQuoteUseCase()
        if(quote != null){
            quoteModel.postValue(quote)
        }

        isloading.postValue(false)
    }


}