package com.reverb.kotlinreview.oop

fun main(){
    val car1 = Car(2019, 4, 5)
    car1.numOfTires = 4
    car1.fuelLevel = 150
    car1.numDoors = 1 //USES THE set() METHOD
    car1.getDoors()
}