package com.example.zadanie.network

import com.example.zadanie.interfaces.ItemsApi
import javax.inject.Inject

class ItemsRepository @Inject constructor(
    private val itemsApi: ItemsApi
){
    suspend fun login(
        password: String,
        grant_type: String,
        client_secret: String,
        client_id: String,
        username: String
    ) = itemsApi.login(
        password,
        grant_type,
        client_secret,
        client_id,
        username)

    suspend fun getItems(token: String, tax: String, category: String) = itemsApi.getItems(token, tax, category)
}