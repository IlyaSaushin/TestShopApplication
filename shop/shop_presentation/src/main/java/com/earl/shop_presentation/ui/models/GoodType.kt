package com.earl.shop_presentation.ui.models

import com.earl.utils.coreUi.Same

data class GoodType (
    val goodType: String,
    val image: Int
) : Same<GoodType> {
    override fun same(value: GoodType) = this == value
}