package com.example.zadanie

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    val api: ItemsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://demo2.gopos.pl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}
