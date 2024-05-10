package com.reverb.kotlinreview.oop

// class <CLASS NAME> constructor { all this is the header class
// the constructor in the header class is the primary constructor

class Person (name:String, age: Int, isAlive: Boolean){
    //class Person  constructor(val name:String, age: Int){
    // if the primary constructor does not have annotations  Person private constructor (val name:String, age: Int)
    // ot visibility modifiers, the 'constructor' keyword can be omitted


    /*
    public fun resetIdentity(nameP: String, ageP: Int){
        this.name = nameP //name in primary constructor should be private
        this.age = ageP //age does not exist
    }
    */

    //if the parameter in the primary constructor
    // does not have val or var, it cannot be modified
    //and can only be used for initializing properties
//    var nombre = "no-name"
//    var edad = 0
//    var vivo = false

//    init {
//        println("Primary Constructor Called")
//        nombre = name
//        edad = age
//        vivo = isAlive
//    }

//    OR I CAN DO
    var nombre = name
    var edad = age
    var vivo = isAlive


    //cant either use var or val in secondary constructor
    constructor(name: String = "Jon", age:Int = -1): this(name, age, false){
        //also calls the primary constructor, so its no necessary to set parameter as done below before
        // this.nombre = name
        // this.edad = age
        // this.vivo = false
    }




}