package com.example.yambprojekt

import android.app.Application

class Yamb: Application() {
    companion object{
        lateinit var application: Application
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}