package com.reverb.kotlinreview

import java.util.UUID

fun main(){
    /*
    for an array of ints,
    better user intArrayOf() primitive
    instead arrayOf()
    */

    val people = mapOf("Jose" to "is 22", "Ana" to "is 35")
    println(people.get("Jose"))
    println(people["Ana"] )


    println( Pair("Tom","Jerry") )
    println( mapOf("Tom" to "cat", "Jerry" to "mouse") )
    println( mapOf( Pair("a","b"), Pair("c","d") ) ); println("\n")


    val airportCodes = listOf("LAX","SFO", "PDX", "SEA")
    val temperatures =
        airportCodes.map {  code -> code to getTemperatureAtAirport(code) }

    for(temp in temperatures){
        //println("Airport code: ${temp.first}, Temperature: ${temp.second}")
        println(temp)
    }
}

private fun getTemperatureAtAirport(code: String): String =
    "${Math.round( Math.random() * 30 ) + code.count() } C°"