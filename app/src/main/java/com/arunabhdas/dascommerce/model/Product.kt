package com.arunabhdas.dascommerce.model

import com.google.gson.annotations.SerializedName

data class Product(
        @SerializedName("name")
        val title: String,

        @SerializedName("date_created")
        val dateCreated: String,

        @SerializedName("photo_url")
        val photoUrl: String,

        @SerializedName("description")
        val description: String,

        @SerializedName("price")
        val price: Double,

        @SerializedName("isOnSale")
        val isOnSale: Int
)