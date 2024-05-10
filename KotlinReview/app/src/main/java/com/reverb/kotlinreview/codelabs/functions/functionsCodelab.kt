package com.reverb.kotlinreview.codelabs.functions

import java.util.Random


fun main(){

    printMessage("Feed the fish")
    feedTheFish()
    println("\n")


    printMessage("Filters")
    val decorations = listOf<String>("rock", "pagoda", "plastic plant", "alligator", "flowerpot")
    println( decorations.filter { item -> item[0] == 'p' } )
    println("\n")


    printMessage("Eager and lazy filters")
    //filters work immediately except if I use sequence
    //with sequences they'll be lazy
    val eager = decorations.filter { it[0] == 'p' }
    println("eager: $eager")
    val filtered = decorations.asSequence().filter { it[0] == 'p' }
    println("filtered: $filtered")
    val newList = filtered.toList()
    println("newList: $newList")

    println("\n---\t---")
    val lazyMap = decorations.asSequence().map {
        println("access: $it")
        it
    }
    println("lazy: $lazyMap")
    println("-----")
    println("first: ${lazyMap.first()}")
    println("-----")
    println("all: ${lazyMap.toList()}")

    println("\n---\t---")

    val lazyMap2 = decorations.asSequence().filter { it[0] == 'p' }.map {
        println("access: $it")
        it
    }
    println("------")
    println("filtered: ${lazyMap2.toList()}")

    printMessage("flatten")

    val mysports = listOf("basketball", "fishing", "running")
    val myplayers = listOf("LeBron James", "Ernest Hemingway", "Usain Bolt")
    val mycities = listOf("Los Angeles", "Chicago", "Jamaica")
    val mylist = listOf(mysports, myplayers, mycities)     // list of lists
    println("-----")
    println("Flat: ${mylist.flatten()}")
}

private fun printMessage(message: String){
    print("\nExercise: $message\n")
}


private fun randomDay(): String{
    val week = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    return week[ week.indices.random() ]
    //return week[ Random().nextInt(week.size) ]
}


private fun fishFood(day: String?)= when(day){
        "Monday"-> "flakes"
        "Wednesday"->"redworms"
        "Thursday"-> "granules"
        "Friday"->"mosquitoes"
        "Sunday"-> "plankton"
        else -> "nothing"

    }


private fun feedTheFish(){
    val day = randomDay()
    val food = fishFood(day)
    println("Today is $day and the fish will eat $food")
    println("Change water: ${shouldChangeWater(day, temperature = 33)}")
}


private fun shouldChangeWater(day: String, temperature: Int =22, dirty: Int =20):Boolean{
    return when{
        day == "sunday" -> true
        temperature > 30 || dirty > 30 -> true
        else -> false
    }
}