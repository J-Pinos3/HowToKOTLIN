package com.reverb.mvvmfirebaserecycler.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reverb.mvvmfirebaserecycler.Usuario
import com.reverb.mvvmfirebaserecycler.domain.data.network.Repo

class MainViewModel: ViewModel() {

    val repo = Repo()
    fun fetchUserData(): LiveData< MutableList<Usuario> >{
        val mutableData = MutableLiveData< MutableList<Usuario> >()

        repo.getUserData().observeForever { userList ->
            mutableData.value = userList
        }

        return  mutableData
    }
}