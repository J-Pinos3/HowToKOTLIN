package com.reverb.kotlinreview

import kotlin.math.ceil
import kotlin.math.floor

fun main(){


    printMessage("Print a sequence of numbers")
    var lim:Int = 7
    printSequence(lim)




    printMessage("Print a pyramid made with numbers")
    val n = 5
    printPyramid(pyramidSize = n)


    printMessage("Test of ceil and floor")
    val l1: Double = 11.305
    val l2: Double = 0.34
    println("ceil($l1) = ${ceil(l1)}")
    println("ceil($l2) = ${ceil(l2)}\n")

    println("floor($l1) = ${floor(l1)}")
    println("floor($l2) = ${floor(l2)}\n")


    
}



private fun printMessage(message: String){
    print("\n\n\nExercise: $message\n")
}


fun printSequence(lim: Int){
    var limit = lim
    do {
        println("Valor: $limit")
        limit--
    }while (limit >= 0)
}


fun printPyramid(pyramidSize: Int = 5): Unit{
    var i: Int = 1

    while (i <= pyramidSize){
        var j = 1
        while(j <= i){
            print("$j ")
            j++
        }
        println()
        i++
    }

    i = pyramidSize-1
    while(i >= 1){
        var j = 1
        while(j <= i){
            print("$j ")
            j++
        }
        println()
        i--
    }
}