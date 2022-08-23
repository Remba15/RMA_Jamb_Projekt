package com.example.yambprojekt.data

fun countOnes(): Int{
    var counter: Int = 0
    listOfDies.forEach{
        if(it.mNumber == 1) counter++
    }
    return counter
}

fun countTwos(): Int{
    var counter: Int = 0
    listOfDies.forEach{
        if(it.mNumber == 2) counter++
    }
    return counter
}

fun countThrees(): Int{
    var counter: Int = 0
    listOfDies.forEach{
        if(it.mNumber == 3) counter++
    }
    return counter
}

fun countFours(): Int{
    var counter: Int = 0
    listOfDies.forEach{
        if(it.mNumber == 4) counter++
    }
    return counter
}

fun countFives(): Int{
    var counter: Int = 0
    listOfDies.forEach{
        if(it.mNumber == 5) counter++
    }
    return counter
}

fun countSixes(): Int{
    var counter: Int = 0
    listOfDies.forEach{
        if(it.mNumber == 6) counter++
    }
    return counter
}