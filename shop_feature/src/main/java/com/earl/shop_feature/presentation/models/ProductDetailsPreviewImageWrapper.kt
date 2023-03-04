package com.earl.shop_feature.presentation.models

import com.earl.utils.coreUi.Same

// Класс-обертка для определения выбранного изображения, которое нужно увеличить при просмотре
// деталей продукта

data class ProductDetailsPreviewImageWrapper(
    var selected: Boolean = false,
    val url: String
) : Same<ProductDetailsPreviewImageWrapper> {
    override fun same(value: ProductDetailsPreviewImageWrapper) = this == value
}
