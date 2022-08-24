package com.example.yambprojekt.data

fun checkForYamb(): Boolean{
    if(countOnes() >= 5) return true
    else if(countTwos() >= 5) return true
    else if(countThrees() >= 5) return true
    else if(countFours() >= 5) return true
    else if(countFives() >= 5) return true
    else if(countSixes() >= 5) return true
    return false
}

fun checkForPoker(): Boolean{
    if(countOnes() >= 4) return true
    else if(countTwos() >= 4) return true
    else if(countThrees() >= 4) return true
    else if(countFours() >= 4) return true
    else if(countFives() >= 4) return true
    else if(countSixes() >= 4) return true
    return false
}

fun checkForStraight(): Boolean{
    val sortedList = sortDieList()
    var counter: Int = 0
    for(i in 0 until sortedList.size - 1){
        if(sortedList[i+1].mNumber - sortedList[i].mNumber == 1){
            counter++
        }
    }
    if(counter >= 4) return true
    return false
}