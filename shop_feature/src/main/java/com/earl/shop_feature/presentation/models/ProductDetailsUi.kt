package com.earl.shop_feature.presentation.models

import com.earl.utils.coreUi.Same

data class ProductDetailsUi (
    val name: String,
    val description: String,
    val rate: Double,
    val reviewsCount: Int,
    val price: Int,
    val colors: List<String>,
    val images: List<String>
) : Same<ProductDetailsUi> {
    override fun same(value: ProductDetailsUi) = value == this

    fun provideImages() : List<ProductDetailsPreviewImageWrapper> {
        val readyList = mutableListOf<ProductDetailsPreviewImageWrapper>()
        for (i in images) {
            readyList.add(
                ProductDetailsPreviewImageWrapper(
                    false,
                    i
                )
            )
        }
        return readyList
    }

    fun provideColors() : List<ProductColorWrapper> {
        val readyList = mutableListOf<ProductColorWrapper>()
        for (i in colors) {
            readyList.add(
                ProductColorWrapper(
                    false,
                    i
                )
            )
        }
        return readyList
    }
}