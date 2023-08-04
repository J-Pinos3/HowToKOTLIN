package com.example.introduccion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class OperadoresComparacion: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val a = 8
        val b = 11

        println("a: " + a)
        println("b: " + b)

        println("a == b: " + (a == b))

        println("a != b: ${a != b}")

        //println("a > b: " + {a > b}) NO DEVUELVE EL VALOR ESPERADO XQ ES EL CUERPO DE UNA FUNCIÓN
        println("a > b: " + (a > b))

        println("a < b: ${a < b}")

        println("a >= b: " + (a >= b))

        println("a <= b: " + (a <= b))

        var nombre: String = "Julión"

        var vip: Boolean = true

        if(vip){
            println("Bienvenido a la zona vip: ${nombre}.")
        }else{
            println("${nombre} no puede entrar a la zona vip.")
        }


    }






}