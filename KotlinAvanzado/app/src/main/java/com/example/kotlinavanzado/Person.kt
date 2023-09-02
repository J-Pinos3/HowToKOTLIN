package com.example.kotlinavanzado

open class Person (var name:String, var passport: String? = null, var heigh: Float = 1.60f){
    var alive: Boolean = true

    fun Person(){
        name = "Juan"
        passport = "05041654"
    }

    fun die(){
        alive = false
    }


    fun checkPolicia(fn: (Float)->Boolean): Boolean{
        return fn(heigh)
    }

}