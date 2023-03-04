package com.earl.shop_feature.data.mappers

import com.earl.shop_feature.data.models.ProductDetailsData
import javax.inject.Inject

class BaseProductDetailsRemoteToDataMapper @Inject constructor() :
    ProductDetailsRemoteToDataMapper<ProductDetailsData> {

    override fun map(
        name: String,
        description: String,
        rate: Double,
        reviewsCount: Int,
        price: Int,
        colors: List<String>,
        images: List<String>
    ) = ProductDetailsData.Base(name, description, rate, reviewsCount, price, colors, images)
}