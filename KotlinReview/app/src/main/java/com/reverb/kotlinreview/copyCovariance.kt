package com.reverb.kotlinreview

fun main() {
    val fruitsBasket1 = Array<Fruit>(3) { _ -> Fruit() }
    val fruitsBasket2 = Array<Fruit>(3) { _ -> Fruit() }
    val things = Array<Any>(3) { _ -> Fruit() }
    val orangesBasket = Array<Orange>(3) {_ -> Orange()}
    val bananaBasket = Array<Banana>(3) { _ -> Banana() }

    copyFromTo(fruitsBasket1, fruitsBasket2)
    //copyFromTo(bananaBasket, fruitsBasket2) TYPE MISMATCH
    copyFromTo2(bananaBasket, fruitsBasket1)

    copyFromTo3(bananaBasket, things)

    //copyFromTo3(bananaBasket, orangesBasket)
}

private fun copyFromTo(from: Array<Fruit>, to: Array<Fruit>){
    for (i in 0 until from.size ){
        to[i] = from[i]
    }
}

private fun copyFromTo2(from: Array<out Fruit>, to: Array<Fruit>){
    //FROM IS READ-ONLY
    //from[0] = Banana() with out, no method that modifies 'from' can be called
    //from.set(i, to[i]) //ERROR

    for (i in 0 until from.size ){
        to[i] = from[i]
    }
}

//CONTRAVARIANCE
private fun copyFromTo3(from: Array<out Fruit>, to: Array<in Fruit>){
    //with in i can set values
    for (i in 0 until from.size ){
        to[i] = from[i]
    }

}