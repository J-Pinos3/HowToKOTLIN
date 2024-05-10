package com.reverb.kotlinreview


fun main(){

    val matriz: Array< Array<Int> > = arrayOf(
        intArrayOf(1,2,3).toTypedArray(),
        arrayOf(4,5,6),
        arrayOf(7,8,9)
    )

    printMatrix(matriz)

    fillMatrix(matriz)

    printMatrix(matriz)

}

private fun fillMatrix(matrix: Array<Array<Int>>){
    for( i in (0 until matrix.size) ){
        for( j in (0 until matrix.size) ){
            matrix[i][j] = (1..10).random()
        }
    }
}

private fun printMatrix(matrix: Array<Array<Int>>){
    for(k in matrix){
        for(elem in k){
            print("$elem ")
        }
        println()
    }

    println("\n")
}