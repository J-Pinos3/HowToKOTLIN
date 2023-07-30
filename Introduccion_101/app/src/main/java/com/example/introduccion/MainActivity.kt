package com.example.introduccion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Date

class MainActivity : AppCompatActivity() {

    companion object{
        const val moneda = "DÓLAR"
    }

    var saldo: Float = 300.55F
    var sueldo: Float = 764.82f
    //var sueldo: Double = 764.82 //no requiere la F(f) y n requiere más memoria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val nombre: String = "José"
        var fecha: String = "30/07/2023"
        val vip: Boolean = false
        var estado: Char = 'S'
        var saludo = "Hola " + nombre


        //nombre = "Lucas" no se puede modificar su contenido porque es val
        println(saludo)
        println("Fecha de retiro: " + fecha)
        println("Nombre: " + nombre)
        println("Moneda: " + moneda)
        println("Saldo: " + saldo.toString())
        println("Sueldo: " + sueldo.toString())
        println("Estado: " + estado + "\n"
        + "Vip: " + vip)
    }
}