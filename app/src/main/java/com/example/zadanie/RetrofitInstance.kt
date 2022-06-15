package com.example.zadanie

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitInstance @Inject constructor() {

    private lateinit var api: ItemsApi

    fun buildApi(): ItemsApi {
        api = Retrofit.Builder()
            .baseUrl("https://demo2.gopos.pl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
        return api
    }
}
