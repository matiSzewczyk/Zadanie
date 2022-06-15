package com.example.zadanie

import dagger.Subcomponent

@Subcomponent
interface AppSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AppSubComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(loginFragment: LoginFragment)
    fun inject(itemsFragment: ItemsFragment)
}