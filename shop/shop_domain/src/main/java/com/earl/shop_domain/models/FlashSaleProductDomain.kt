package com.earl.shop_domain.models

import com.earl.shop_domain.mappers.FlashSaleProductDomainToUiMapper

interface FlashSaleProductDomain {

    fun <T> mapToUi(mapper: FlashSaleProductDomainToUiMapper<T>) : T

    class Base(
        private val category: String,
        private val name: String,
        private val price: Double,
        private val discount: Int,
        private val imageUrl: String,
    ) : FlashSaleProductDomain {

        override fun <T> mapToUi(mapper: FlashSaleProductDomainToUiMapper<T>) =
            mapper.mapToUi(category, name, price, discount, imageUrl)
    }
}