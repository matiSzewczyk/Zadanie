package com.example.zadanie

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule() {

    @Singleton
    @Provides
    fun provideApi(retrofitInstance: RetrofitInstance): ItemsApi {
        return retrofitInstance.buildApi()
    }
}