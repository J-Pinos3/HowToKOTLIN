package com.example.holamundo.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.holamundo.R

class PrimeraAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primera_app)
        //esta función se llama al arrancar la pantalla

        val botonStart = findViewById<AppCompatButton>(R.id.btnStart)

        val editName_txt = findViewById<AppCompatEditText>(R.id.editNametxt)
        val msg = editName_txt.text.toString()

        botonStart.setOnClickListener {
            Log.i("José Pinos","Botón Pulsado, el mensaje es: " + msg)

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("EXTRA_NAME", msg)
            startActivity(intent)
        }

    }
}