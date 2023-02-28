package com.earl.shop_presentation.ui.models

import com.earl.utils.coreUi.Same

data class ProductBrand(
    val brandType: String,
    val brandName: String,
    val logo: Int
) : Same<ProductBrand> {

    override fun same(value: ProductBrand) = this == value
}
