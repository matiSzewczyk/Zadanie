package com.example.zadanie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemsViewModel : ViewModel() {

    var itemList = MutableLiveData<Item>()

    suspend fun getItemList() {
        val response = ItemsRepository(RetrofitInstance.api).getItems("Bearer $TOKEN")
        if (response.isSuccessful) {
            itemList.postValue(response.body()!!)
        }
    }
}