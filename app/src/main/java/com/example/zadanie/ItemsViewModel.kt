package com.example.zadanie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemsViewModel : ViewModel() {

    val token = MutableLiveData<Login>()
    var errorMsg = MutableLiveData<String>()
    var loginSuccess = false

    suspend fun login(password: String, login: String) {
        val response = ItemsRepository(RetrofitInstance.api).login(
            password,
            "password",
            "dc6d8a5e-861b-4df8-bb6b-9889c106161d",
            "073481d0-549e-4eac-9174-27cd2432f149",
            login
        )
        if (response.isSuccessful) {
            token.postValue(response.body())
            println("token: ${response.body()!!.access_token}")
            loginSuccess = true
        } else {
            errorMsg.postValue(response.errorBody()!!.string())
            Log.e("Error: ", "Login failed.")
        }
    }
}