package com.example.yambprojekt.data

val listOfDies = listOf<Die>(
    Die(mId = 1),
    Die(mId = 2),
    Die(mId = 3),
    Die(mId = 4),
    Die(mId = 5),
    Die(mId = 6),
)

/*fun printDieNumbers(){
    listOfDies.forEach{
        println("Die ${it.mId} number = ${it.mNumber}")
    }
    println("_______________________________________")
}*/

fun throwUnlockedDies(){
    listOfDies.forEach{
        if(!it.mLocked) it.throwDie()
    }
}

fun sortDieList(): List<Die> {
    return listOfDies.sortedBy { it.mNumber }
}