package com.earl.shop_data.models

import com.earl.shop_data.mappers.LatestProductDataToDomainMapper

interface LatestProductData {

    fun <T> mapToDomain(mapper: LatestProductDataToDomainMapper<T>) : T

    class Base(
        private val category: String,
        private val name: String,
        private val price: Int,
        private val imageUrl: String
    ) : LatestProductData {
        override fun <T> mapToDomain(mapper: LatestProductDataToDomainMapper<T>) =
            mapper.mapToDomain(category, name, price, imageUrl)
    }
}