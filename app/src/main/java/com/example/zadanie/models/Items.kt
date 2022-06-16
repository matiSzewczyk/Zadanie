package com.example.zadanie.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Items(
    @Id
    var id: Long = 0,
    var name: String? = null,
    var price: Double? = null,
    var currency: String? = null,
    var imageLink: String? = null,
    var tax: String? = null,
    var category: String? = null
)
