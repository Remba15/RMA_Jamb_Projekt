package com.example.yambprojekt.data

import com.example.yambprojekt.R

val listOfDies = listOf<Die>(
    Die(mId = 1),
    Die(mId = 2),
    Die(mId = 3),
    Die(mId = 4),
    Die(mId = 5),
    Die(mId = 6),
)

fun getDieResource(number: Int): Int {
    return when(number){
        1 -> R.drawable.die_face_1
        2 -> R.drawable.die_face_2
        3 -> R.drawable.die_face_3
        4 -> R.drawable.die_face_4
        5 -> R.drawable.die_face_5
        6 -> R.drawable.die_face_6
        else -> R.drawable.red_cell
    }
}

fun sortDieList(): List<Die> {
    return listOfDies.sortedBy { it.mNumber }
}