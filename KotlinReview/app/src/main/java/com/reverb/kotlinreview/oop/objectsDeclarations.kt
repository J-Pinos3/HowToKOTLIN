package com.reverb.kotlinreview.oop

//OBJECT DECLARATION IS USED
//TO CREATE A SINGLETON

//SINGLETON = class with a single instance
object Util{
    fun numberOfProcessors() =
        Runtime.getRuntime().availableProcessors()
}

fun main(){
    println("I've ${Util.numberOfProcessors()} processors ")
}