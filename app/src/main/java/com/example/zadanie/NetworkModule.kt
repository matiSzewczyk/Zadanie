package com.example.zadanie

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