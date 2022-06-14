package com.example.zadanie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Response
import javax.inject.Inject

class ItemsViewModel @Inject constructor(
    private val repository: ItemsRepository
) : ViewModel() {

    var itemList = MutableLiveData<MutableList<Items>>()
    private var itemsBox = ObjectBox.store.boxFor(Items::class.java)

    suspend fun getItemList() {
        val response = repository.getItems(
            "Bearer $TOKEN",
            "tax",
            "category"
        )
        if (response.isSuccessful) {
            sendToBox(response)
        }
    }

    private fun sendToBox(response: Response<Item>) {
        response.body()?.data?.forEach {

            val query = itemsBox.query(Items_.name.equal(it.name))
                .build()
            val results = query.find()
            if (results.isNotEmpty())  return

            val item = Items(
                0,
                it.name,
                it.price.amount,
                it.price.currency,
                it.image_link?.default_link,
                it.tax.name,
                it.category.name
            )
            itemsBox.put(item)
        }
    }

    fun getFromBox() {
        val items = itemsBox.all
        if (items.isNotEmpty()) {
            itemList.postValue(items)
        }
    }
}