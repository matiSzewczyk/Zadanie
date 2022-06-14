package com.example.zadanie

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =[
    NetworkModule::class
])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(itemsFragment: ItemsFragment)
    fun inject(loginFragment: LoginFragment)
}