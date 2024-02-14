package com.reverb.logregfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {

    private lateinit var emailRegister: TextInputEditText
    private lateinit var passwordRegister: TextInputEditText
    private lateinit var btnRegister: Button
    private lateinit var pbRegister: ProgressBar
    private lateinit var tvLoginNow: TextView
    private lateinit var auth: FirebaseAuth

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            goToMain()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initUI()
        auth = FirebaseAuth.getInstance()

        val email = emailRegister.text.toString()
        val password = passwordRegister.text.toString()

        if(email.isNullOrEmpty() ){
            emailRegister.error = "Enter Email"
            btnRegister.isEnabled = false
        }else{
            btnRegister.isEnabled = true
        }

        if(password.isNullOrEmpty()){
            passwordRegister.error = "Enter Password"
            btnRegister.isEnabled = false
        }else{
            btnRegister.isEnabled = true
        }


        tvLoginNow.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }


        btnRegister.setOnClickListener {

            pbRegister.visibility = View.VISIBLE

            auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@Register, "Account Created.", Toast.LENGTH_SHORT,).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Register, "Authentication failed.", Toast.LENGTH_SHORT,).show()
                }
                pbRegister.visibility = View.GONE
            }

        }



    }//ON CREATE

    private fun initUI(){
        emailRegister = findViewById(R.id.emailRegister)
        passwordRegister = findViewById(R.id.passwordRegister)
        btnRegister = findViewById(R.id.btnRegister)
        pbRegister = findViewById(R.id.pbRegister)
        tvLoginNow = findViewById(R.id.tvLoginNow)
    }

    private fun goToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}