package com.reverb.kotlinreview

fun main(){

    printMessage("Array(int, function)")
    val arr1 = Array(5){ element -> Math.pow((element+1.0),2.0)   }//.sum

    for(k in arr1){
        print("$k ")
    }
    println("\nSum of elements: ${arr1.sum()}")
    println("\n")


    printMessage("listOf")
    //can not be modified
    val fruits = listOf("apple","watermelon","banana","kiwi")//List<String>
    //fruits[0] = "" ERROR
    for (fruit in fruits.indices)
        print("${fruits[fruit]} ")
    println("\n")



    printMessage("arrayListOf")
    //can be modified
    val dishes = arrayListOf("shawarma","taco","hamburger",56)//ArrayList<String>
    dishes.add("pizza")
    dishes[0] = "kebab"
    for(dish in dishes)
        print("$dish ")
    println("\n")


    printMessage("mutableListOf")
    //can be modified
    val cars = mutableListOf("corvette","mazda","hyundai","toyota")// MutableList<String>
    cars.add("suzuki")
    cars[0]="corvata"
    for(car in cars)
        print("$car ")
    println("\n")


}


private fun printMessage(message: String){
    print("\n\nExercise: $message\n")
}