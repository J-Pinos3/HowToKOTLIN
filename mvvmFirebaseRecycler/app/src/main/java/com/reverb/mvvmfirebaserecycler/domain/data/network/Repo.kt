package com.reverb.mvvmfirebaserecycler.domain.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.reverb.mvvmfirebaserecycler.Usuario

class Repo {

    fun getUserData(): LiveData<MutableList<Usuario>>{
        val mutableData = MutableLiveData< MutableList<Usuario> >()

        FirebaseFirestore.getInstance().collection("Usuarios").get().addOnSuccessListener {result ->

            val listData = mutableListOf<Usuario>()
            for(document in result){
                val imageUrl = document.getString("imageUrl")
                val name = document.getString("name")
                val description = document.getString("description")

                val usuario = Usuario(imageUrl!!, name!!, description!!)
                listData.add(usuario)
            }
            mutableData.value = listData
        }

        return mutableData
    }


}//REPO CLASS