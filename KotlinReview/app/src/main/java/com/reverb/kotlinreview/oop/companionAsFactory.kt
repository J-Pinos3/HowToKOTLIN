package com.reverb.kotlinreview.oop
/**
 *  FACTORY IS AN OBJECT THAT CREATES ANOTHER OBJECT
 */


class MachineOperatorAsFactory private constructor (val name:String){
    fun checkin() = checkedIn++
    fun checkout() = checkedIn--

    companion object{
        var checkedIn = 0
        fun minimumBreak():String = "20 minutes every 2 hours"

        fun create(name:String): MachineOperatorAsFactory{
            val objetoss: MachineOperatorAsFactory = MachineOperatorAsFactory(name)
            objetoss.checkin()

            return objetoss
        }
    }
}


fun main(){

    val machine = MachineOperatorAsFactory.create("John Deere")
    println(MachineOperatorAsFactory.checkedIn)

}