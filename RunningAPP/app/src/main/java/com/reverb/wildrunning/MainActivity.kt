package com.reverb.wildrunning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.reverb.wildrunning.LoginActivity.Companion.userEmail

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Toast.makeText(this, "Bienvenido $userEmail", Toast.LENGTH_SHORT).show()

    }

    fun callSignOut(view: View){
        signOut()
    }

    private fun signOut(){
        userEmail = ""

        FirebaseAuth.getInstance().signOut()
        //go back to start
        startActivity(Intent(this, LoginActivity::class.java))
    }
}