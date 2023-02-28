package com.earl.shop_domain.mappers

interface LatestProductDomainToUiMapper <T> {

    fun mapToUi(
        category: String,
        name: String,
        price: Int,
        imageUrl: String
    ) : T
}