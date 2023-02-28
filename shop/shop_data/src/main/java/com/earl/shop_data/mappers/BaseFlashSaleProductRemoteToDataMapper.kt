package com.earl.shop_data.mappers

import com.earl.shop_data.models.FlashSaleProductData
import javax.inject.Inject

class BaseFlashSaleProductRemoteToDataMapper @Inject constructor() : FlashSaleProductRemoteToDataMapper<FlashSaleProductData> {

    override fun mapToData(
        category: String,
        name: String,
        price: Double,
        discount: Int,
        imageUrl: String
    ) = FlashSaleProductData.Base(category, name, price, discount, imageUrl)
}