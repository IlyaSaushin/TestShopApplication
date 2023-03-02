package com.earl.shop_data.mappers

interface ProductDetailsDataToDomainMapper<T> {

    fun map(
        name: String,
        description: String,
        rate: Double,
        reviewsCount: Int,
        price: Int,
        colors: List<String>,
        images: List<String>
    ) : T
}