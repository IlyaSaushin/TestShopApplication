package com.earl.utils.remoteDataSource

import okhttp3.ResponseBody
import retrofit2.http.GET

interface NetworkService {

    @GET("/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun fetchLatestProducts() : ResponseBody

    @GET("/v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun fetchFlashSaleProducts() : ResponseBody

    @GET("/v3/f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun fetchProductDetails() : ResponseBody
}