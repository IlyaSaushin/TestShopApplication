package com.earl.shop_data.mappers

interface FlashSaleProductDataToDomainMapper <T> {

    fun mapToDomain(
        category: String,
        name: String,
        price: Double,
        discount: Int,
        imageUrl: String
    ) : T
}