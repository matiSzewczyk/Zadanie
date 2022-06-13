package com.example.zadanie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemsViewModel : ViewModel() {

    var itemList = MutableLiveData<Item>()
    private var itemsBox = ObjectBox.store.boxFor(Items::class.java)

    suspend fun getItemList() {
        val response = ItemsRepository(RetrofitInstance.api).getItems(
            "Bearer $TOKEN",
            "tax",
            "category"
        )
        if (response.isSuccessful) {
            itemList.postValue(response.body()!!)
        }
    }
    fun sendToBox() {
        itemList.value!!.data?.forEach {
            val query = itemsBox.query(Items_.id.equal(it.id))
                .build()
            val results = query.find()
            if (results.isNotEmpty()) return
            else {
                val item = Items(
                    it.id.toLong(),
                    it.name,
                    it.price.amount,
                    it.price.currency,
                    it.image_link.small
                )
                itemsBox.put(item)
            }
        }
    }
}