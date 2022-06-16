package com.example.zadanie.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zadanie.network.ItemsRepository
import com.example.zadanie.ObjectBox
import com.example.zadanie.UserPreferences
import com.example.zadanie.models.Item
import com.example.zadanie.models.Items
import com.example.zadanie.models.Items_
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val repository: ItemsRepository,
    private val preferences: UserPreferences
) : ViewModel() {

    var itemList = MutableLiveData<MutableList<Items>>()
    private var itemsBox = ObjectBox.store.boxFor(Items::class.java)

    suspend fun getItemList() {
        val response = repository.getItems(
            "Bearer " + preferences.getToken().toString(),
            "tax",
            "category"
        )
        if (response.isSuccessful) {
            sendToBox(response)
        } else {
            val errorString= JSONObject(response.errorBody()!!.string()).getString("error_description")
            Log.e(TAG, errorString )
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