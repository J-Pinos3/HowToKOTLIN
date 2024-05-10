package com.reverb.kotlinreview.oop

class Car (private val yearOfMake: Int, public var numOfTires:Int,  theDoors: Int){
    //primary constructor
    //private read-only, *** public read-write *** can not have val or var (its just a parameter val)

    /*********
     * ANOTHER PROPERTIES BELOW BUT NOT IN THE CONSTRUCTOR
     * SO WE CAN AVOID CHANGING ITS VALUES
     * IT'S NOT A GOOD IDEA TO HAV MUTABLE (var) IN THE CONSTRUCTOR
     * theDoors IS JUST A PARAMETER
     *************/
    var fuelLevel = 100
    //unless numDoors is public I cannot use it outside Car class
    var numDoors = theDoors //if this is val I can not use set()
        set(value) {
            if(value < 0){
                throw RuntimeException("No Imaginaries, plox")
            }
            field = value
            //field == numDoors
        }

    init {
        fuelLevel =5
    }

    fun getDoors():Int = numDoors
}