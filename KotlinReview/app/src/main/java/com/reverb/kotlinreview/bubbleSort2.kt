package com.reverb.kotlinreview

//val asda: ArrayList<Int> = arrayListOf(2,65)
//val arreglo: ArrayList<Int> = arrayListOf(2,5,9,8,0,1,3) //0 1 2 3 5 8 9




fun main() {
    val arreglo: Array<Int> = arrayOf(2, 5, 9, 8, 0, 1, 3)

    showMessage("Original Array")
    showArray2(arreglo)

    bubbleSort2(arreglo)

    showMessage("Sorted Array")
    showArray2(arreglo)
}

private fun showMessage(msg: String) {
    println("\n$msg")
}

fun showArray2(arr: Array<Int>) {
    for (k in arr) {
        print("$k ")
    }
    println()
}

fun bubbleSort2(arr: Array<Int>) {
    val n = arr.size

    for (i in 0 until n - 1) {
        for (j in 0 until n - i - 1) {
            if (arr[j] > arr[j + 1]) {
                val tmp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = tmp
            }
        }
    }
}



