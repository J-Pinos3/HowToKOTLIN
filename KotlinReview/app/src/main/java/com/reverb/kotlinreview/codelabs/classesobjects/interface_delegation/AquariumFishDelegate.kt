package com.reverb.kotlinreview.codelabs.classesobjects.interface_delegation

import com.reverb.kotlinreview.codelabs.classesobjects.AquariumFish
import com.reverb.kotlinreview.codelabs.classesobjects.FishAction

interface FishColorDelegation{
    val color: String
}

interface FishActionDelegation{
    fun eat()
}

//SINGLETON
object GoldColor: FishColorDelegation{
    override val color:String = "gold"

}

class Shark() : FishColorDelegation by GoldColor, FishActionDelegation {

    override val color: String = "green"

    override fun eat() {
        println("hunt and eat fishes")
    }
}

//instead of implementing FishColorDelegation, use the implementation provided by GoldColor
class Plecostomus: FishColorDelegation by GoldColor, FishActionDelegation {
    override val color = "gold"

    override fun eat() {
        println("eat algae")
    }
}

/*
instead of implementing FishColorDelegation, use the implementation provided by GoldColor
all plecostomus will be gold :(
class Plecostomus(fishColor:FishColorDelegation = GoldColor ): FishColorDelegation by fishColor, FishActionDelegation {
    WITHIN THE CONSTURCTOR GoldColor IS THE DEFAULT COLOR FOR Plecostomus
    override val color = "gold"

    override fun eat() {
        println("eat algae")
    }
}
*/
