package com.example.zadanie

import com.google.gson.annotations.SerializedName

data class LoginInfo(
    @SerializedName("password")
    var password: String,
    @SerializedName("grant_type")
    val grant_type: String = "password",
    @SerializedName("client_secret")
    val client_secret: String = "dc6d8a5e-861b-4df8-bb6b-9889c106161d",
    @SerializedName("client_id")
    val client_id: String = "073481d0-549e-4eac-9174-27cd2432f149",
    @SerializedName("username")
    var username: String
    )
