package com.earl.shop_feature.presentation.models

import com.earl.utils.coreUi.Same

data class ProductDetailsPreviewImageWrapper(
    var selected: Boolean = false,
    val url: String
) : Same<ProductDetailsPreviewImageWrapper> {
    override fun same(value: ProductDetailsPreviewImageWrapper) = this == value
}
