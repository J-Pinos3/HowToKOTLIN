package com.reverb.kotlinreview.oop

object Sun: Runnable{

    val radiusKM = 696000
    var coreTemperatureInCels = 150000000

    override fun run() {
        println("Spinning")
    }

}

fun moveIt(runnable: Runnable){
    runnable.run()
}


fun main(){
    println("RADIUS: " + Sun.radiusKM)
    moveIt(Sun)
}