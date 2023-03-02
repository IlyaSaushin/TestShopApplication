package com.earl.shop_domain.models

import com.earl.shop_domain.mappers.ProductDetailsDomainToUiMapper

interface ProductDetailsDomain {

    fun <T> mapToUi(mapper: ProductDetailsDomainToUiMapper<T>) : T

    class Base(
        private val name: String,
        private val description: String,
        private val rate: Double,
        private val reviewsCount: Int,
        private val price: Int,
        private val colors: List<String>,
        private val images: List<String>
    ) : ProductDetailsDomain {

        override fun <T> mapToUi(mapper: ProductDetailsDomainToUiMapper<T>) =
            mapper.map(name, description, rate, reviewsCount, price, colors, images)
    }
}