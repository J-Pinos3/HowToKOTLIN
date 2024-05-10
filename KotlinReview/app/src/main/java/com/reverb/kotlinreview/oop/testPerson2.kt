package com.reverb.kotlinreview.oop

fun main() {
    val a = Person2("Brian",20, true)

    println("name: ${a.name}")
    println("age: ${a.age}")
    println("live: ${a.alive}\n\n")


    var b = Person2()

    println("name: ${b.name}")
    println("age: ${b.age}")
    println("live: ${b.alive}\n\n")


    //a and are the same object
    //=== compares references in kotlin
    //== compares references in java

    //== compares values in kotlin
    //equals() in java compares values
    b = a
    if(a === b){
        println("Same reference")
    }else
        println("Different reference")

    println("a.name: ${a.name}")
    println("b.name set to xd")
    b.name = "xd"
    println("a.name: ${a.name}")
//    java.io.File(".")
//        .walk()
//        .filter { file->file.extension == "kt" }
//        .forEach {
//            println(it) }
}