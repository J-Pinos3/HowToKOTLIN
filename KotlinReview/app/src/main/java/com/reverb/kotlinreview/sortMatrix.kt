package com.reverb.kotlinreview


fun main(){

    val matriz: Array< Array<Int> > = arrayOf(
        intArrayOf(1,2,3).toTypedArray(),
        arrayOf(4,5,6),
        arrayOf(7,8,9)
    )
    //matriz.forEach { println(it.size)    }
    println("MATRIZ ORIGINAL\n")
    fillMatrixRandomValues(matriz)
    printMatrix(matriz)

    sortMatrix(matriz)

    println("\n\nMATRIZ ORDENADA\n")
    printMatrix(matriz)

}

private fun fillMatrixRandomValues(matrix: Array<Array<Int>>){
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



private fun sortMatrix( matrix: Array<Array<Int>> ) {
    var sum = 0
    matrix.forEach { subArray -> sum += subArray.size }

    val  matrixTotalSize= sum

    val arregloTemp:IntArray = IntArray(matrixTotalSize)
    fillNormalArray(matrix, arregloTemp)
    bubbleSort3(arregloTemp)

    refillMatrixWithArray(matrix, arregloTemp)
}

fun bubbleSort3( arr: IntArray){

    for(i in 0.. arr.size-1 ){
        for(j in i+1 until arr.size  ){
            if( arr[j] < arr[i] ){
                var tmp: Int  = arr[i]
                arr[i] = arr[j]
                arr[j] = tmp
            }
        }
    }

}

private fun refillMatrixWithArray(matrix: Array<Array<Int>>, arr: IntArray){
    var k = 0
    for(i in 0 until matrix.size ){
        for(j in 0 until matrix.size){
            matrix[i][j] = arr[k]
            k++
        }
    }
}

private fun fillNormalArray(matrix: Array<Array<Int>>, arr: IntArray){
    var k = 0
    for(i in 0 until matrix.size ){
        for(j in 0 until matrix.size){
            arr[k] = matrix[i][j]
            k++
        }
    }
}