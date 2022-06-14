package com.example.zadanie

import android.app.Application

class MyApplication : Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }
}