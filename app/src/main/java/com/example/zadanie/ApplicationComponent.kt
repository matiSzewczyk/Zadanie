package com.example.zadanie

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =[
    NetworkModule::class,
    SubComponentModule::class
])
interface ApplicationComponent {
    fun appSubComponent(): AppSubComponent.Factory
}