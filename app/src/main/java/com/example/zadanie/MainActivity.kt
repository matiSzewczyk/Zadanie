package com.example.zadanie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

var TOKEN =""

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}