package com.example.yambprojekt.data

class TableCell(
    var mText: String = "_"
) {
    //properties
    private var mFilled: Boolean = false
    //method
    fun fillCell(){
        mFilled = true
    }
}