package com.earl.shop_feature.data.mappers

import com.earl.shop_feature.domain.models.ProductDetailsDomain
import javax.inject.Inject

class BaseProductDetailsDataToDomainMapper @Inject constructor() :
    ProductDetailsDataToDomainMapper<ProductDetailsDomain> {

    override fun map(
        name: String,
        description: String,
        rate: Double,
        reviewsCount: Int,
        price: Int,
        colors: List<String>,
        images: List<String>
    ) = ProductDetailsDomain.Base(
        name, description, rate, reviewsCount, price, colors, images
    )
}