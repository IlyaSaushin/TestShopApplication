package com.earl.shop_data.mappers

import com.earl.shop_domain.models.LatestProductDomain
import javax.inject.Inject

class BaseLatestProductDataToDomainMapper @Inject constructor() : LatestProductDataToDomainMapper<LatestProductDomain> {

    override fun mapToDomain(
        category: String,
        name: String,
        price: Int,
        imageUrl: String
    ) = LatestProductDomain.Base(
        category, name, price, imageUrl
    )
}