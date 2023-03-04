package com.earl.shop_feature.presentation.mappers

import com.earl.shop_feature.domain.mappers.FlashSaleProductDomainToUiMapper
import com.earl.shop_feature.presentation.models.FlashSaleProductUi
import javax.inject.Inject

class BaseFlashSaleProductDomainToUiMapper @Inject constructor() :
    FlashSaleProductDomainToUiMapper<FlashSaleProductUi> {

    override fun mapToUi(
        category: String,
        name: String,
        price: Double,
        discount: Int,
        imageUrl: String
    ) = FlashSaleProductUi.Base(
        category, name, price, discount, imageUrl
    )
}