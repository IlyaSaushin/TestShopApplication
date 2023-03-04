package com.earl.shop_feature.presentation.models

import com.earl.utils.coreUi.Same

data class ProductBrand(
    val brandType: String,
    val brandName: String,
    val logo: Int
) : Same<ProductBrand> {

    override fun same(value: ProductBrand) = this == value
}
