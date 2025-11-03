package com.dark.wfh
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction

 data class HomeData(
    val smartphones: List<Product>,
    val laptops: List<Product>
)

class ProductRepository(private val apiService: ApiService) {

    fun fetchHomeData(): Single<HomeData> {
        return Single.zip(
            apiService.getSmartphones(),
            apiService.getLaptops(),
            BiFunction { smartphoneResponse, laptopResponse ->
                HomeData(
                    smartphones = smartphoneResponse.products,
                    laptops = laptopResponse.products
                )
            }
        )
    }
}