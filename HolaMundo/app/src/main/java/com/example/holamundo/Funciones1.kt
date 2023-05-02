package com.example.holamundo

import kotlin.math.pow
import kotlin.math.sqrt

fun main(){
    mensaje()

    val numero: Double = 9.0

    println("Cuadrado de ${numero}: " + cuadrado_numero(numero))
    println("Cubo de ${numero}: " +  cubo_numero(numero))
    println("Raíz de ${numero}: " +  raiz_numero(numero) + "\n\n")

    var valor2: Double = ecuacion(4)
    println( ecuacion(4) )
    println(valor2)

}


fun mensaje(){
    println("Tema: Funciones en kotlin" )
}

fun ecuacion(valor: Int): Double {
    return valor.toDouble().pow(2.0) + sqrt(valor / 3.0)
}

fun cuadrado_numero(num: Double): Double{

    return num.pow(2.0)
}

fun cubo_numero(num: Double): Double{
    return num.pow(3.0)
}

fun raiz_numero(num: Double): Double{
    return sqrt(num)
}

