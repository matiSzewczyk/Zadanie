package com.example.zadanie

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class UserPreferences @Inject constructor(
    private val context: Context
){

    companion object {
        val TOKEN = stringPreferencesKey("TOKEN_NAME")
    }

    suspend fun saveToken(token: Token) {
        context.dataStore.edit {
            it[TOKEN] = token.name.toString()
        }
    }

    suspend fun getToken(): Any? {
        val value = context.dataStore.data
            .map {
                it[TOKEN]
            }
        return value.firstOrNull()
    }
}