package com.reverb.kotlinreview.oop

class Person2 (_name:String, _age: Int, _isAlive: Boolean = false) {
    var name: String
    var age: Int = 0 //lateinit is not supported in primitive types
    var alive: Boolean = false

    init {
        println("Primary Constructors' init ")
        name = _name
        age = _age
        this.alive = _isAlive
    }

    constructor(): this("Jhon Doe", 33, false){

    }

}