package com.reverb.logregfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var emailLogin: TextInputEditText
    private lateinit var passwordLogin: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var pbLogin: ProgressBar
    private lateinit var tvRegisterNow: TextView
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
        setContentView(R.layout.activity_login)

        initUI()

        auth = FirebaseAuth.getInstance()

        val email = emailLogin.text.toString()
        val password = passwordLogin.text.toString()

        if(email.isNullOrEmpty() ){
            emailLogin.error = "Enter Email"
            btnLogin.isEnabled = false
        }else{
            btnLogin.isEnabled = true
        }

        if(password.isNullOrEmpty()){
            passwordLogin.error = "Enter Password"
            btnLogin.isEnabled = false
        }else{
            btnLogin.isEnabled = true
        }


        tvRegisterNow.setOnClickListener {
            goToRegister()
        }



        btnLogin.setOnClickListener {

            pbLogin.visibility = View.VISIBLE
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@Login, "Login Successful.", Toast.LENGTH_SHORT,).show()
                        goToMain()
                    } else {
                        Toast.makeText(this@Login, "Authentication failed.", Toast.LENGTH_SHORT,).show()
                    }
                    pbLogin.visibility = View.GONE
            }

        }

    }//ON CREATE



    private fun initUI() {
        emailLogin = findViewById(R.id.email)
        passwordLogin = findViewById(R.id.password)
        btnLogin = findViewById(R.id.btnLogin)
        pbLogin = findViewById(R.id.pbLogin)
        tvRegisterNow = findViewById(R.id.tvRegisterNow)
    }

    private fun goToRegister(){
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}