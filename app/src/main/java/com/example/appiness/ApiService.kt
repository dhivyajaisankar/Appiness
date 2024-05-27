package com.example.appiness

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("products.php")
    fun getProducts(): Call<ApiResponse>


}