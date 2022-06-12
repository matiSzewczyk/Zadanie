package com.example.zadanie

import android.app.Application

class Zadanie : Application() {
    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }
}