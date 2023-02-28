package com.earl.shop_data.models.remote

import com.earl.shop_data.mappers.FlashSaleProductRemoteToDataMapper
import com.earl.shop_data.models.FlashSaleProductData

data class FlashSaleProductRemote(
    val category: String,
    val name: String,
    val price: Double,
    val discount: Int,
    val imageUrl: String
) {
    fun mapToData(mapper: FlashSaleProductRemoteToDataMapper<FlashSaleProductData>) =
        mapper.mapToData(category, name, price, discount, imageUrl)
}