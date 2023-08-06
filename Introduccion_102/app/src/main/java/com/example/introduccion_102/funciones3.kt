package com.example.introduccion_102

import kotlin.math.PI
import kotlin.math.pow

fun main(){


    println("El área de un cuadrado de lado l = 2.45 es: ${areaCuadrado(2.45)}")
    println("El perímetro de un cuadrado de lado l = 2.45 es: ${perimetroCuadrado(2.45)}\n\n")


    println("El área de un círculo de radio r = 4.77 es: ${areaCirculo(4.77)}")
    println("El perímetro de un círculo de radio l = 4.77 es: ${perimetroCirculo(4.77)}\n\n")

    println("El área de un triángulo de base = 6.0 y altura = 4.77 es: ${areaTriangulo(6.0, 4.77)}")
}


fun areaCuadrado(lado: Double ): Double{
    return lado.pow(2.0)
}

fun perimetroCuadrado(lado: Double ): Double{
    return 4 * lado
}


fun areaCirculo(radio: Double): Double{
    return PI *  radio * radio
}

fun perimetroCirculo(radio: Double): Double{
    return 2.0 * PI *  radio * radio
}


fun areaTriangulo(base: Double, altura: Double): Double{
    return base * altura / 2.0
}