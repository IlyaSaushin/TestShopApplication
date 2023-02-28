package com.earl.shop_presentation.ui.mappers

import com.earl.shop_domain.mappers.FlashSaleProductDomainToUiMapper
import com.earl.shop_presentation.ui.models.FlashSaleProductUi
import javax.inject.Inject

class BaseFlashSaleProductDomainToUiMapper @Inject constructor() : FlashSaleProductDomainToUiMapper<FlashSaleProductUi> {

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