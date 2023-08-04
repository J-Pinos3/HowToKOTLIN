package com.example.introduccion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class IfElse: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val anio: Int = 1800

        if( (anio % 4 != 0) || (anio % 100 == 0 && anio % 400 != 0) ){
            println("El año " + anio + " no es bisiesto")
        }else{
            println("El año " + anio + " es bisiesto")
        }


        var cadenita: String = "Hola Mundo, Bienvenidos a Kotlin"
        var cadena2: String = "Información de texto"
        var sonIguales: Int = (cadenita.compareTo(cadena2))

        if( sonIguales != 0 ){
            println("No son iguales")
        }else{
            println("Son iguales")
        }


    }


}