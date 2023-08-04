package com.example.introduccion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WhenExpression :AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       var diaSemana: Int = 9

        println( "Hoy es: " + when(diaSemana){
            1 -> "Lunes"
            2 -> "Martes"
            3 -> "Miércoles"
            4 -> "Jueves"
            5 -> "Viernes"
            6 -> "Sábado"
            7 -> "Domingo"
            else -> "No existe ese Día"

        })

    }

}