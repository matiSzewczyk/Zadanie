package com.example.zadanie

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Items(
    @Id
    var id: Int = 0,
    var name: String? = null,
    var price: Double? = null,
    var currency: String? = null,
    var imageLink: String? = null
)
