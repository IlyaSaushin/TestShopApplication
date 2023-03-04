package com.earl.shop_feature.data.mappers

interface FlashSaleProductRemoteToDataMapper<T> {

    fun mapToData(
        category: String,
        name: String,
        price: Double,
        discount: Int,
        imageUrl: String
    ) : T
}