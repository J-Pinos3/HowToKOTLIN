package com.example.testpeletas

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.testpeletas.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //es igual a var textViewMensaje: TextView = findViewById(R.id.tvMensaje)
        var textViewMensaje = findViewById<TextView>(R.id.tvMensaje)

        textViewMensaje.apply {
            setTextColor(Color.BLUE)
            text = "Texto cambiado desde código"
            setTypeface(Typeface.MONOSPACE, Typeface.BOLD)
        }

//        textViewMensaje.setTextColor(Color.BLUE)
//        textViewMensaje.text = "Texto cambiado desde código"
//        textViewMensaje.setTypeface(Typeface.MONOSPACE, Typeface.BOLD)


        textViewMensaje.setOnClickListener {
            Toast.makeText(this, "Nuevo Texto On Click", Toast.LENGTH_SHORT).show()
            textViewMensaje.setTextColor(Color.RED)
        }


    }
}