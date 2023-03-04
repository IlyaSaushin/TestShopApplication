package com.earl.shop_feature.data.models

import com.earl.shop_feature.data.mappers.FlashSaleProductDataToDomainMapper

interface FlashSaleProductData {

    fun <T> mapToDomain(mapper: FlashSaleProductDataToDomainMapper<T>) : T

    class Base(
        private val category: String,
        private val name: String,
        private val price: Double,
        private val discount: Int,
        private val imageUrl: String,
    ) : FlashSaleProductData {

        override fun <T> mapToDomain(mapper: FlashSaleProductDataToDomainMapper<T>) =
            mapper.mapToDomain(category, name, price, discount, imageUrl)
    }
}