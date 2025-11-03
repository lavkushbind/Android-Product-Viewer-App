package com.dark.wfh

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String,
    val images: List<String>
)

data class ProductListResponse(
    val products: List<Product>
)