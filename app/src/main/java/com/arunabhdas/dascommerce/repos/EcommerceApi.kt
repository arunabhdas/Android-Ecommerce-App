package com.arunabhdas.dascommerce.repos

import com.arunabhdas.dascommerce.model.Product
import retrofit2.http.GET

interface EcommerceApi {

    @GET("api/ecommerce/v1/allProducts")
    suspend fun fetchAllProducts(): List<Product>

    @GET("api/products")
    suspend fun fetchProducts(): List<Product>
}
