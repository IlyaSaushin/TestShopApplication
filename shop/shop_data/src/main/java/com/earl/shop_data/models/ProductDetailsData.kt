package com.earl.shop_data.models

import com.earl.shop_data.mappers.ProductDetailsDataToDomainMapper

interface ProductDetailsData {

    fun <T> mapToDomain(mapper: ProductDetailsDataToDomainMapper<T>) : T

    class Base(
        private val name: String,
        private val description: String,
        private val rate: Double,
        private val reviewsCount: Int,
        private val price: Int,
        private val colors: List<String>,
        private val images: List<String>
    ) : ProductDetailsData {

        override fun <T> mapToDomain(mapper: ProductDetailsDataToDomainMapper<T>) =
            mapper.map(name, description, rate, reviewsCount, price, colors, images)
    }
}