package com.example.zadanie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemsViewModel : ViewModel() {

    var itemList = MutableLiveData<Item>()
    var itemsBox = ObjectBox.store.boxFor(Items::class.java)

    suspend fun getItemList() {
        val response = ItemsRepository(RetrofitInstance.api).getItems("Bearer $TOKEN")
        if (response.isSuccessful) {
            itemList.postValue(response.body()!!)
        }
    }
    fun sendToBox() {
        itemList.value!!.data?.forEach {
            val item = Items(
                it.id,
                it.name,
                it.price.amount,
                it.price.currency,
                it.image_link.small
            )
            itemsBox.put(item)
        }
    }
}