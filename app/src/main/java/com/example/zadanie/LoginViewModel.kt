package com.example.zadanie

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: ItemsRepository,
    private val preferences: UserPreferences
): ViewModel() {

    var errorMsg = MutableLiveData<String>()
    var loginSuccess = false

    suspend fun login(password: String, login: String) {
        val response = repository.login(
            "zadanie",
            "password",
            "dc6d8a5e-861b-4df8-bb6b-9889c106161d",
            "073481d0-549e-4eac-9174-27cd2432f149",
            "zadanie@zadanie.com"
        )
        if (response.isSuccessful) {
            preferences.saveToken(Token(response.body()!!.access_token))
            loginSuccess = true
        } else {
            val errorString= JSONObject(response.errorBody()!!.string()).getString("error_description")
            errorMsg.postValue(errorString.toString())
            Log.e(TAG, "Login failed. ")
        }
    }
}