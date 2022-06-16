package com.example.zadanie.di

import com.example.zadanie.interfaces.ItemsApi
import com.example.zadanie.network.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideApi(retrofitInstance: RetrofitInstance): ItemsApi {
        return retrofitInstance.buildApi()
    }
}