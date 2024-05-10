package com.reverb.kotlinreview.oop

fun drawCircle(){
    val circle = object {//OBJECT EXPRESSION
        val x = 10
        val y = 20
        val radius = 30
    }

    println(
        "Circle x: ${circle.x}, y: ${circle.y}, radius: ${circle.radius}"
    )
}

fun createRunnable(): Runnable{
    val runnable = object: Runnable{//OBJECT EXPRESSION
        override fun run() {    println("You Called bro... \n")    }
    }

    return runnable
}

//if theres more than one interface
//i've to specify the instance it should return
//if return runnable, cannot use close() and viceversa OUTSIDE the function
fun createRunnable2(): AutoCloseable = object: Runnable,  AutoCloseable{//OBJECT EXPRESSION
    override fun run() {
        println("You Called...")
    }

    override fun close() {
        println("Closing and then")
        run()
        /*
        the function returns AutoCloseable so i cannot
        call bRunnable.run()
        but I can call run inside this method
        */
    }

}

fun main(){

    drawCircle()
    val aRunnable = createRunnable()
    aRunnable.run()

    val bRunnable = createRunnable2()//returns AutoCloseable
    bRunnable.close()
}