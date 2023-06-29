package com.example.holamundo.estrucdecision

import kotlin.math.pow

fun main(){

    print("Ingrese su peso: ")
    var peso: Double = readln().toDouble()
    print("\nIngrese su altura: ")
    var altura: Double = readln().toDouble()

    val indice:Double = BMI_calculator(altura, peso)

    println("\nSu imc es: ${indice}")
    if (indice <= 18.4)
        println("Estado: PESO BAJO")
    else if(indice >= 18.5 && indice <= 24.9)
        println("Estado: NORMAL")
    else if( indice in 25.0..29.9)
        println("Estado: SOBREPESO")
    else
        println("ERROR")
}


fun BMI_calculator(altura: Double, peso: Double): Double = peso / altura.pow(2.0)

