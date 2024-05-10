package com.reverb.kotlinreview

var factor = 2
fun main(){
    var message = "the factor is $factor"//determined in compilation time so keeps 2

    factor = 0
    println(doubleIt(2))
    println(message)
}

fun doubleIt(n: Int) = n * factor