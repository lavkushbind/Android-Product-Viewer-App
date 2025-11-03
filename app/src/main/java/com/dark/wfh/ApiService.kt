package com.dark.wfh
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {
    @GET("products/category/smartphones")
    fun getSmartphones(): Single<ProductListResponse>

    @GET("products/category/laptops")
    fun getLaptops(): Single<ProductListResponse>
}