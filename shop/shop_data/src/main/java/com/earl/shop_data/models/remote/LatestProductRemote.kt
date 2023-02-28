package com.earl.shop_data.models.remote

import com.earl.shop_data.mappers.LatestProductRemoteToDataMapper
import com.earl.shop_data.models.LatestProductData

data class LatestProductRemote (
    val category: String,
    val name: String,
    val price: Int,
    val imageUrl: String
) {
    fun mapToData(mapper: LatestProductRemoteToDataMapper<LatestProductData>) =
        mapper.mapToData(category, name, price, imageUrl)
}