package com.reverb.kotlinreview
//val asda: ArrayList<Int> = arrayListOf(2,65)
//val arreglo: ArrayList<Int> = arrayListOf(2,5,9,8,0,1,3) //0 1 2 3 5 8 9

fun main() {


    val arreglo: IntArray = intArrayOf(2,5,9,8,0,1,3) //0 1 2 3 5 8 9 SE PASA POR VALOR A LA FUNCIÓN SORT Y NUNCA SE ORDENA


    showMessage("Original Array")
    showArray(arreglo)

    bubbleSort(arreglo)

    showMessage("Sorted Array")
    showArray(arreglo)
}

private fun showMessage(msg: String){
    println("\n$msg")
}

fun showArray(arr: IntArray){
    for(k in arr){
        print("$k ")
    }
    println()
}


fun bubbleSort( arr: IntArray){

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
