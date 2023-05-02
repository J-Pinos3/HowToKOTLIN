package com.example.holamundo

fun main(){
    var num1:Int = 15
    var num2:Int = 9
    var cadena: String = "Bienvenido: "
    cadena += "José"

    println(cadena)
    println("Valor1: " + num1 + " Valor2: $num2" + "\n")
    println("Suma: " + "${num1 + num2}")
    println("Resta: " + "${num1 - num2}")
    println("Multiplicacion: " + "${num1 * num2}")
    println("División (enteros): " + "${num1 / num2}")
    println("División (reales): " + "${num1.toDouble() / num2}")
}