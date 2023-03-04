package com.earl.shop_feature.data.mappers

interface LatestProductDataToDomainMapper <T> {

    fun mapToDomain(
        category: String,
        name: String,
        price: Int,
        imageUrl: String
    ) : T
}