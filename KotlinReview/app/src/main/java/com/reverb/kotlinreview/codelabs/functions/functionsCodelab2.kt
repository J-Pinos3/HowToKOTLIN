package com.reverb.kotlinreview.codelabs.functions

fun main(){
    val dirtyLevel = 20

    val waterFilter = {dirty: Int -> dirty/2}
    //val waterFilter: (Int) -> Int = {dirty: Int -> dirty/2}
    /*
    the code above means: make a variable called waterFilter,
    waterFilter can be any function that takes an Int and returns an Int,
    assign a lambda to waterFilter,
    the lambda returns tha value of the argument "dirty" divided by 2
    */
    println(waterFilter(dirtyLevel))

    println( updateDirty(dirty = 50, operation = waterFilter) )
    println(updateDirty(10) { it: Int -> it + 1 })

    println( updateDirty(15, ::increaseDirty) )
    //println( updateDirty(15, ::waterFilter) ) cannot put a reference to a variable
}

private fun updateDirty(dirty: Int = 30, operation: (Int) -> Int): Int{
    return operation(dirty)
}

private fun increaseDirty(start: Int) = start+1