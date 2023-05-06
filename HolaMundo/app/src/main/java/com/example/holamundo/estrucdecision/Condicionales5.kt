package com.example.holamundo.estrucdecision

fun main(){

    val num1: Double =  7.5
    val num2: Double =  3.45

    println(sumar(num1, num2))
    println(restar(num1, num2))
    println(multiplicar(num1, num2))
    println(dividir(num1, num2))
    println(dividir(num1,0.0))
    println(dividir(num1))
}

fun sumar(num1: Double, num2: Double): Double{
    return num1 + num2
}


fun restar(num1: Double, num2: Double): Double{
    return num1 - num2
}


fun multiplicar(num1: Double, num2: Double): Double{
    return num1 * num2
}


fun dividir(num1: Double, num2: Double = 1.0): Double{
    if(num2 == 0.0){
        return num1/1.0
    }
    return num1 / num2
}