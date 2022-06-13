package com.example.zadanie

import android.content.Context
import io.objectbox.BoxStore

object ObjectBox {
    lateinit var store: BoxStore
    private set

    fun init(context: Context) {
        println("\nxd")
        store = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }
}