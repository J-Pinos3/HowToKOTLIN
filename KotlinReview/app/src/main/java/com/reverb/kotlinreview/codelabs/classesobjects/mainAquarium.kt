package com.reverb.kotlinreview.codelabs.classesobjects

fun main(){
    buildAquarium3()
}


fun buildAquarium(){
    val myAquarium = Aquarium()
    myAquarium.printSize()
    myAquarium.height=-135
    myAquarium.printSize()
}

fun buildAquarium1(){
    val myAquarium = Aquarium()
    myAquarium.printSize()

    val myAquarium1 = Aquarium(width = 25)
    myAquarium1.printSize()

    val myAquarium2 = Aquarium(height = 30, length = 110)
    myAquarium2.printSize()

    val myAquarium3 = Aquarium(width = 25, height = 30, length = 220)
    myAquarium3.printSize()

}

fun buildAquarium2(){
    val myAquarium = Aquarium(29)
    myAquarium.printSize()
    println("Volume: ${myAquarium.width * myAquarium.length * myAquarium.height / 1000} liters")
}

fun buildAquarium3(){
    val myAquarium = Aquarium(29)
    myAquarium.printSize()
    myAquarium.volume = 70
    myAquarium.printSize()

}