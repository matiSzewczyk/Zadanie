package com.example.zadanie

import kotlin.math.log

class ItemsRepository(
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