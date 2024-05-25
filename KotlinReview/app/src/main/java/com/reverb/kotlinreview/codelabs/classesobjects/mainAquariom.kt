package com.reverb.kotlinreview.codelabs.classesobjects

fun main(){
    buildAquariom()
}


fun buildAquariom(){
    val myAquariom = Aquariom(length = 25, width = 25, height = 40)

    myAquariom.printSize()
    myAquariom.volume = 300
    myAquariom.printSize()

    println("\n")

    val myTowerTank = TowerTank(40,25)
    myTowerTank.printSize()
}
