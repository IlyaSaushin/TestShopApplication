package com.earl.shop_feature.presentation.mappers

import com.earl.shop_feature.domain.mappers.ProductDetailsDomainToUiMapper
import com.earl.shop_feature.presentation.models.ProductDetailsUi
import javax.inject.Inject

class BaseProductDetailsDomainToUiMapper @Inject constructor() :
    ProductDetailsDomainToUiMapper<ProductDetailsUi> {

    override fun map(
        name: String,
        description: String,
        rate: Double,
        reviewsCount: Int,
        price: Int,
        colors: List<String>,
        images: List<String>
    ) = ProductDetailsUi(
        name, description, rate, reviewsCount, price, colors, images
    )
}