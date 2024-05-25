package com.reverb.kotlinreview.codelabs.classesobjects

import kotlin.math.PI

open class Aquariom(open var length: Int = 100, open var width: Int = 20, open var height: Int = 40){

    open var volume:Int
        get(){
            return width * height *length/100
        }

        set(value){
            height = (value*1000)/(width*length)
        }


    open val shape = "rectangle"

    open var water: Double = 0.0
        get() = volume * 0.9

    fun printSize() {
        println(shape)
        println("Width: $width cm " +
                "Length: $length cm " +
                "Height: $height cm ")
        // 1 l = 1000 cm^3
        println("Volume: $volume liters Water: $water liters (${water / volume * 100.0}% full)")
    }

}

class TowerTank (override var height: Int, var diameter: Int): Aquariom(height = height, width = diameter, length = diameter){
    override var volume: Int
        get() = (width/2 * length/2 * height/1000 * PI).toInt()
        set(value) {
            height = ( (value*1000/PI) / (width/2 * length/2) ).toInt()
        }

    //WIDTH IS THE DEFAULT VALUE OF SUPER CLASS
    override var water: Double= volume * 0.80

    override val shape: String = "cylinder"
}