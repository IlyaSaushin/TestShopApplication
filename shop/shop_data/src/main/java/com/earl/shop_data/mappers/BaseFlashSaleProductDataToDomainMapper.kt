package com.earl.shop_data.mappers

import com.earl.shop_domain.models.FlashSaleProductDomain
import javax.inject.Inject

class BaseFlashSaleProductDataToDomainMapper @Inject constructor() : FlashSaleProductDataToDomainMapper<FlashSaleProductDomain> {

    override fun mapToDomain(
        category: String,
        name: String,
        price: Double,
        discount: Int,
        imageUrl: String
    ) = FlashSaleProductDomain.Base(
        category, name, price, discount, imageUrl
    )
}