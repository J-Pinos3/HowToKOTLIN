package com.example.introduccion_102

fun main(){



    var musica: MutableMap <Int, String> = mutableMapOf(

        1 to "Traveler in Time",
        2 to "Master of Puppets",
        3 to "Told You So",
        4 to "Lie",
        5 to "My Own Grave"

    )

    println(musica.toString() + "\n")

    musica.put(6,"As I Am")

    println(musica.toString() + "\n")
    println(musica.get(1))
}