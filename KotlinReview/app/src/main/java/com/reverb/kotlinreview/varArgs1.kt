package com.reverb.kotlinreview

fun main(){
    println(max(1,2,3,4,5,6,7,9,10,5))

    val lista1: IntArray = intArrayOf(2,5,9,8,11,4,0)
    val lista2: Array<Int> = arrayOf(6,9,88,4,3,0,4,11)

    println(max(*lista1))
    println(max(*lista2.toIntArray()))
}

fun max(vararg numbers: Int):Int{
    var largeOne = numbers[0]
    for(number in numbers){
        largeOne = if (number > largeOne) number else largeOne
    }

    return largeOne
}