package com.reverb.kotlinreview.oop

import javax.crypto.Mac

class MachineOperator(val name: String){
    fun checkin() =  checkedIn++
    fun checkout() = checkedIn--

    /**COMPANION OBJECTS CAN ALSO HAVE A NAME*/
    companion object MachineOperatorFactory{
        var checkedIn = 0//equivalent of c++ static
        fun minimumBreak():String = "20 minutes every 2 hours"
    }
}

fun main(){
    MachineOperator("MAX").checkin()
    println( MachineOperator.minimumBreak() )
    println( MachineOperator.checkedIn )

    //val ref = MachineOperator.Companion WITHOUT NAME
    val ref = MachineOperator.MachineOperatorFactory
    ref.checkedIn
    ref.minimumBreak()

}