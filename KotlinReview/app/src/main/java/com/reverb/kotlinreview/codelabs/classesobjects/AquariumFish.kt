package com.reverb.kotlinreview.codelabs.classesobjects

//abstract classes can have constructors
abstract class AquariumFish {
    abstract val color: String
}

//interfaces cannot have constructors
interface FishAction{
    fun eat()
}

class Shark() : AquariumFish(), FishAction{
    //CLASSES MUST IMBLEMENT ABSTRACT PROPERTIES OR METHODS
    override val color: String = "green"
    //get(): String = field WIHTOUT field it giuves me an error
    //get(): String = color ERROR


    override fun eat() {
        println("hunt and eat fishes")
    }
}


class Plecostomus: AquariumFish(), FishAction{
    override val color = "gold"

    override fun eat() {
        println("eat algae")
    }
}