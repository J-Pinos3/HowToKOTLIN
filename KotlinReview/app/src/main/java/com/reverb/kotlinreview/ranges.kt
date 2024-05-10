package com.reverb.kotlinreview

import androidx.core.util.toRange

fun main(){
    // INT RANGE
    val sevenToFifteen = 7..15
    for(k in sevenToFifteen){
        print("$k ")
    }
    println("\n")


    // INT RANGE STEP
    val eightToThirteen = 7..13 step 2
    for(k in eightToThirteen){
        print("$k ")
    }
    println("\n")


    // CHAR RANGE
    val abecedaryShort = 'a'..'e'
    for(k in abecedaryShort){
        print("$k ")
    }
    println("\n")


    // CLOSED RANGE
    val seek4Help = "helk".."help"
    /* needs an iterator
    for(k in seek4Help )
        print("$k ")
    println("\n")
    */
    println(seek4Help.contains("hell"))
    println(seek4Help.contains("helm"))
    println(seek4Help.contains("helq"))
    println("\n")


    // DOWN TO
    //val down = 5.downTo(1)
    for(num in 10 downTo-10 ){
        print("$num ")
    }
    println("\n")


    // DOWN TO STEP
    for(num in 10 downTo-10 step 3 ){
        print("$num ")
    }
    println("\n")


    // UNTIL
    for(num in 20 until 25){
        print("$num ")
    }
    println("\n")


    // UNTIL STEP
    for(num in 22 until 31 step 2){
        print("$num ")
    }
    println("\n")

    //<X>ArrayOf is for primitive types
    //int instead INteger



}//main