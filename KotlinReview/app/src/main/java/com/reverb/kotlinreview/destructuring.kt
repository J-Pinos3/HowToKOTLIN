package com.reverb.kotlinreview

fun main(){
    //WE CAN ALSO DESTRUCTURE A DATA CLASS
    val result = getFullName()
    println(result.first + " " + result.second + " is ${result.third}y.o." )

    val (first, _, third) = getFullName2()
    println("$first  is $third y.o.")

}


fun getFullName() = Triple("John", "Petrucci", 50)
fun getFullName2() = Triple("Kevin", "Myers", 37)