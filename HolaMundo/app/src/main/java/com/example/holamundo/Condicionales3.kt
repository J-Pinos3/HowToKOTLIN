package com.example.holamundo

import kotlin.math.ceil
import kotlin.math.round

fun main(){

    val l1: Float = 13.5f
    val l2: Float = 7.49f


    if(l1 < 0 || l2 < 0){
        println("Los lados de un triángulo no pueden ser valores negativos")
    }else{

        println("El área del triángulo es: ${ceil((l1*l2) /2.0 )}")
    }

}