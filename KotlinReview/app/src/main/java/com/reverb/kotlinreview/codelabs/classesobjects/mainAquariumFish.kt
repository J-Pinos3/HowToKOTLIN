package com.reverb.kotlinreview.codelabs.classesobjects

fun main(){
    makeFish()
}

fun makeFish(){
    val shark = Shark()
    val plecostomus = Plecostomus()

    println("Shark: ${shark.color}")
    shark.eat()
    println("Plecostomus: ${plecostomus.color}")
    plecostomus.eat()
}