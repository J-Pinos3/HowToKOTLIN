package com.reverb.kotlinreview.codelabs.codelabbasickotlinconcepts

import kotlin.reflect.KFunction0

fun main() {
    //classic function call
    trick()

    //use the function reference operator ::
    //trickFunction datatype is a reference
    val trickFunction = ::trick
    trickFunction()


    println("-----")


    //use a variable that its data tpy is a function with no parameters and return unit
    //function variables
    lambdaTrick()

    //assign function lambda variable to a variable
    val trickLambdaFunction = lambdaTrick
    trickLambdaFunction()


    println("-----")


    //lambda as a datatype
    treat()

    //function as datatype
    treatFunction()


    println("*****")


    //VALID LAMBDA FUNCTION
    //val coinsFun1:(Int) -> String = fun(a: Int): String{  return a.toString()  }
    val coinsFun1: (Int) -> String = {
        quantity -> "$quantity quarters"
    }

    val coinsFun2: (Int) -> String = {
        "$it quarters"
    }



    //trickOrTreat(true)() auto execute the function
    //reference to function
    val treatFunction = trickOrTreat(false, coinsFun1)
    //lambda out of parentheses
    val trickFunctionVar = trickOrTreat(true) { candys -> "We want $candys candys" }
    treatFunction()
    trickFunctionVar()


    println("*****")



    //repeat() is a option to for
    repeat(times = 5) {
        print("${it} ")

    }


    println("\nPRACTICE")

    val morningNotification = 51
    val eveningNotification = 135

    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)

    println("\nPRACTICE 2")
    val child = 5
    val adult = 28
    val senior = 87

    val isMonday = true

    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")


}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    return when{
        age in 1 .. 12 -> 15
        age in 13 until 60 && isMonday -> 25
        age in 13 until 60 && !isMonday -> 30
        age in 60 .. 100 -> 20
        else -> -1
    }
}


fun printNotificationSummary(numberOfMessages: Int) {
    if(numberOfMessages >= 100){
        println("Your phone is blowing up! You have 99+ notifications.")
    }else{
        println("You've $numberOfMessages notifications")
    }
}


fun trick(): Unit{
    println("No treats")
}

val lambdaTrick = {
    println("No treats Lambda")
}

val treat: () -> Unit = {
    println("Have a treat!!")
}

val treatFunction = fun (): Unit{
    println("Have a treat function!!")
}

//returns a function
//fun trickOrTreat(isTrick: Boolean, extraTreat: (Int)->String ): () -> Unit{
//extraTreat is nullable, can contain a function or null
fun trickOrTreat(isTrick: Boolean, extraTreat: ((Int)->String)? ): () -> Unit{
    return if (isTrick){
        lambdaTrick
    }else{
        if (extraTreat != null) {
            println(extraTreat(5))
        }
        treat        //treatFunction
    }
}


open class Phone(open var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }

    fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone(var isFolded: Boolean = false): Phone(){

     override var isScreenLightOn = false



    override fun switchOn() {
        if (isFolded)
            isScreenLightOn = true
    }

    fun closePhone(){
        val phoneState = if (isFolded) "closing" else "opening"
        //isScreenLightOn from parent
        println("$phoneState your phone")
    }
}