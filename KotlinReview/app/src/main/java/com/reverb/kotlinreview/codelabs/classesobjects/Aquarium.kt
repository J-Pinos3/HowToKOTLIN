package com.reverb.kotlinreview.codelabs.classesobjects

//MORE COMPACT
//kotlin create setters and getters automatically
//class Aquarium (var length: Int = 100, var width: Int = 20, var height: Int = 40){
//I can remove attributes inside the class
class Aquarium (length: Int = 100, width: Int = 20, height: Int = 40){
    var width: Int = width
    var height: Int = height
    var length: Int = length

    var volume:Int
        get() = width * height * length / 1000//1000^cm3 == 1 liter
        set(value    ) {
            //recalculates height based on amount of water
            height = (value*1000)/ (width* length)
        }

    fun printSize(){
        println("Width: $width cm. " +
             "Height: $height cm. "  +
             "Lenght: $length cm. "+
             "Volume: $volume lt.")
    }

    init {
        println("Initializing aquarium")
    }

    init {
        println("Still building aquarium object")
    }


    constructor(numberOfFish: Int):this(){
        //primary constructor is called first
        //2000cm^3 per fish +extra room so water doesn't spill
        val tank = numberOfFish * 2000 * 1.1

        height = (tank / (length * width)).toInt()
    }


}