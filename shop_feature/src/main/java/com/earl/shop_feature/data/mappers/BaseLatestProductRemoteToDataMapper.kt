package com.earl.shop_feature.data.mappers

import com.earl.shop_feature.data.models.LatestProductData
import javax.inject.Inject

class BaseLatestProductRemoteToDataMapper @Inject constructor() :
    LatestProductRemoteToDataMapper<LatestProductData> {

    override fun mapToData(
        category: String,
        name: String,
        price: Int,
        imageUrl: String
    ) = LatestProductData.Base(
        category, name, price, imageUrl
    )
}