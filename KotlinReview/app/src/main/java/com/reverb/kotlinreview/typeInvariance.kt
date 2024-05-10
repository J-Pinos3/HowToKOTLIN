package com.reverb.kotlinreview

open class Fruit
class Banana: Fruit()
class Orange: Fruit()

fun main(){

    val bananas: Array<Banana> = arrayOf()
    val oranges: List<Orange> = listOf()

    //BOTH ARRAYS LIST WORK THE SAME
    //val apple: ArrayList<Banana> = arrayListOf()
    //apple.add(Banana())
    //apple[0] = Banana()
    //val orange: java.util.ArrayList<Orange> = arrayListOf()
    //orange.add(Orange())
    //orange[0] = Orange()

    //ARRAY<T> is mutable
    //bananas[0] = Banana() NO PROBLEM
    //receiveFruits(bananas) //ERROR expected array of fruit

    //LIST<T> is immutable
    //oranges[0] = Orange() ERROR AND KILL YOUR SELF
    receiveFruits2(oranges)
}


private fun receiveFruits(fruits: Array<Fruit>){
    println("Number of Fruits: ${fruits.size}")
}

private fun receiveFruits2(fruits: List<Fruit>){
    println("Number of Fruits: ${fruits.size}")
}