package com.reverb.kotlinreview.oop

fun main() {
    var a: Person = Person()
    println("name: ${a.nombre}")
    println("age: ${a.edad}")
    println("live: ${a.vivo}\n\n")


    var b: Person = Person("Kevin", 30, true)
    println("name1: ${b.nombre}")
    println("age1: ${b.edad}")
    println("live1: ${b.vivo}\n\n")


}