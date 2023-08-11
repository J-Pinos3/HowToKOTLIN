package com.example.introduccion_102

fun main(){

    var matriz = arrayOf(
        intArrayOf(1,2,3),
        intArrayOf(4,5,6,7,8,9),
        intArrayOf(10,11,12)
    )


    for(i in (0 until matriz.size) ){

        for(j in (0 until matriz[i].size) ){
            println("Posición[$i][$j]:  ${matriz[i][j]}")
        }
        println()
    }


}