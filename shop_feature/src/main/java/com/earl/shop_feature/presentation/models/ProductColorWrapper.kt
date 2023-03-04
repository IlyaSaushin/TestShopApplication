package com.earl.shop_feature.presentation.models

// Класс-обертка для определения выбранного цвета, который нужно увеличить при просмотре
// деталей продукта

data class ProductColorWrapper(
    var isSelected: Boolean = false,
    val color: String
)
