package com.example.holamundo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.holamundo.firstapp.PrimeraAppActivity
import com.example.holamundo.imccalculator.ImcCalculatorActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnAppSaludo = findViewById<Button>(R.id.btnAppSaludo)
        btnAppSaludo.setOnClickListener{ navega_AppSaludo() }

        val btnAppIMC = findViewById<Button>(R.id.btnAppIMC)
        btnAppIMC.setOnClickListener { navega_AppIMC() }

    }


    private fun navega_AppIMC() {
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }


    private fun navega_AppSaludo(){
        val intent = Intent(this, PrimeraAppActivity::class.java)
        startActivity(intent)
    }

}