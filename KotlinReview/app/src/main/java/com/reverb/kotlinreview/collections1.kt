package com.reverb.kotlinreview

fun main(){

    val names = listOf("Tom","Jerry","Chilly","Steve")
    val games = arrayListOf<String>("Minecraft","Simpsons","Smite","Galaxy Life")

    val countries: List<String> = listOf("Ecuador","Mexico","Perú")
    val countries2: List<String> = arrayListOf("Germany","Finland","Netherlands")
    //val countries3: ArrayList<String> = listOf<String>("","","").toTypedArray() ERROR
    //val countries3: ArrayList<String> = listOf<String>("","","")// ERROR
    val countries3: ArrayList<String> = arrayListOf("","","")



    println(names.javaClass)
    println(games.javaClass)

    println("Names")
    for( (index, value) in names.withIndex() ){
        println("$index  $value")
    }
    println("\n")

    println("Games")
    for( (index, value) in games.withIndex() ){
        println("$index  $value")
    }
    println("\n")

    println("Latam")
    for( k in countries.indices ){
        println("${countries[k]} ")
    }
    println("\n")

    println("Europe")
    for( k in countries2.indices ){
        println("${countries2[k]} ")
    }
    println("\n")


}
