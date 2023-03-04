package com.earl.shop_feature.data.mappers

interface FlashSaleProductDataToDomainMapper <T> {

    fun mapToDomain(
        category: String,
        name: String,
        price: Double,
        discount: Int,
        imageUrl: String
    ) : T
}