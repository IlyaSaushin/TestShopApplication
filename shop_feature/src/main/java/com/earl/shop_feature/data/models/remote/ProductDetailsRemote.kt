package com.earl.shop_feature.data.models.remote

import com.earl.shop_feature.data.mappers.ProductDetailsRemoteToDataMapper
import com.earl.shop_feature.data.models.ProductDetailsData

data class ProductDetailsRemote (
    val name: String,
    val description: String,
    val rate: Double,
    val reviewsCount: Int,
    val price: Int,
    val colors: List<String>,
    val images: List<String>
) {
    fun mapToData(mapper: ProductDetailsRemoteToDataMapper<ProductDetailsData>) =
        mapper.map(name, description, rate, reviewsCount, price, colors, images)
}