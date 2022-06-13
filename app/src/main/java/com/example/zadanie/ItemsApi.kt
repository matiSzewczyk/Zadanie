package com.example.zadanie

import retrofit2.Response
import retrofit2.http.*

interface ItemsApi {

    // Get the token
    @POST("oauth/token")
    @FormUrlEncoded
    suspend fun login(
        @Field("password") password: String,
        @Field("grant_type") grant_type: String,
        @Field("client_secret") client_secret: String,
        @Field("client_id") client_id: String,
        @Field("username") username: String,
    ) : Response<Login>

    // Get the list of items
    @GET("api/v3/27/items")
    suspend fun getItems(
        @Header("Authorization") token: String,
        @Query("include") tax: String,
        @Query("include") category: String
    ) : Response<Item>
}