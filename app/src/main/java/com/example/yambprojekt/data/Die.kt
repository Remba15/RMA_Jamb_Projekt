package com.example.yambprojekt.data

import kotlin.random.Random

class Die(var mId: Int)
{
    //Properties
    var mNumber: Int = 0
    var mLocked: Boolean = false

    //Methods
    fun throwDie(): Unit{
        if(!mLocked) {
            mNumber = Random.nextInt(1, 6)
        }
    }
    fun lockDie(): Unit{
        mLocked = true
    }
    fun unlockDie(): Unit{
        mLocked = false
    }
}