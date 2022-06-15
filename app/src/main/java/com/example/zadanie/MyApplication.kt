package com.example.zadanie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }
}