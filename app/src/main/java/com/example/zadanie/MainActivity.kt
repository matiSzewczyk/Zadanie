package com.example.zadanie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

var TOKEN =""

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    lateinit var appSubComponent: AppSubComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        appSubComponent = (application as MyApplication)
            .appComponent.appSubComponent().create()
        appSubComponent.inject(this)
        super.onCreate(savedInstanceState)
    }
}