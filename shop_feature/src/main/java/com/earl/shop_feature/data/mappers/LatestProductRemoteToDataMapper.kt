package com.earl.shop_feature.data.mappers

interface LatestProductRemoteToDataMapper<T> {

    fun mapToData(
        category: String,
        name: String,
        price: Int,
        imageUrl: String
    ) : T
}