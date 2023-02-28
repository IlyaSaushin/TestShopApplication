package com.earl.shop_presentation.ui.mappers

import com.earl.shop_domain.mappers.LatestProductDomainToUiMapper
import com.earl.shop_presentation.ui.models.LatestProductUi
import javax.inject.Inject

class BaseLatestProductDomainToUiMapper @Inject constructor() : LatestProductDomainToUiMapper<LatestProductUi> {

    override fun mapToUi(
        category: String,
        name: String,
        price: Int,
        imageUrl: String
    ) = LatestProductUi.Base(
        category, name, price, imageUrl
    )
}