package com.earl.shop_feature.domain.mappers

interface FlashSaleProductDomainToUiMapper<T> {

    fun mapToUi(
        category: String,
        name: String,
        price: Double,
        discount: Int,
        imageUrl: String
    ) : T
}