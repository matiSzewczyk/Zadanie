package com.example.zadanie

import android.content.Context
import com.example.zadanie.models.MyObjectBox
import io.objectbox.BoxStore

object ObjectBox {
    lateinit var store: BoxStore
    private set

    fun init(context: Context) {
        store = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }
}