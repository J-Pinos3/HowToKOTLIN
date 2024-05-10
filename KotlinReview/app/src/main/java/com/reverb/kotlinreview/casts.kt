package com.reverb.kotlinreview

class Animal{

    //if i comment the equals function, all comparisons will be false
    override fun equals(other: Any?): Boolean = other is Animal
}

fun main(){

    val greet:Any ="hello"
    val saludo: String = "hola"
    val odie: Any = Animal()
    val toto: Any = Animal()
    val garfield:Animal = Animal()
    val dragon = Animal()

    println(odie == greet) //false
    println(odie == toto) //true

    println(toto == saludo) //false
    println(odie == garfield) //true

    //println(dragon == saludo) error
    println(dragon == odie)//true



}