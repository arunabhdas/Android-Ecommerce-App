package com.arunabhdas.dascommerce.repos

import com.arunabhdas.dascommerce.model.Product
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

const val BASE_URL_HTTPS = "https://finepointmobile.com/"
const val BASE_URL_HTTP = "https://mountshastaplatform.herokuapp.com/"

class ProductsRepository {

    private fun retrofit(): EcommerceApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_HTTP)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(EcommerceApi::class.java)
    }

    suspend fun fetchAllProductsRetrofit(): List<Product> {
        // TODO-FIXME-DEBUG return retrofit().fetchAllProducts()
        return retrofit().fetchProducts()
    }

    fun getAllProducts(): Single<List<Product>> {
        return Single.create<List<Product>> {
            it.onSuccess(fetchProducts())
        }
    }

    suspend fun searchForProducts(term: String): List<Product> {
        return fetchAllProductsRetrofit().filter { it.title.contains(term, true) }
    }

    fun getProductByName(name: String): Single<Product> {
        return Single.create<Product> {
            val product = fetchProducts().first { it.title == name }
            it.onSuccess(product)
        }
    }

    fun fetchProducts(): List<Product> {
        val json =
            URL("https://gist.githubusercontent.com/arunabhdas/df34a33a06e985d85f2ba7f6e635c600/raw/df0aeaea2def4aa1c9b0f266d032b6733c3fbaaa/shopping_products.json").readText()
        return Gson().fromJson(json, Array<Product>::class.java).toList()
    }
}
