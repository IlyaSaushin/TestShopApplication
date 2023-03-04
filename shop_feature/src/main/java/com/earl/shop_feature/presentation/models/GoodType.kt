package com.earl.shop_feature.presentation.models

import com.earl.utils.coreUi.Same

data class GoodType (
    val goodType: String,
    val image: Int
) : Same<GoodType> {
    override fun same(value: GoodType) = this == value
}