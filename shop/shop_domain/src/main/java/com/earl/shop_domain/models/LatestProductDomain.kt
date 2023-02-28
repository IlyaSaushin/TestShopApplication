package com.earl.shop_domain.models

import com.earl.shop_domain.mappers.LatestProductDomainToUiMapper

interface LatestProductDomain {

    fun <T> mapToUi(mapper: LatestProductDomainToUiMapper<T>) : T

    class Base(
        private val category: String,
        private val name: String,
        private val price: Int,
        private val imageUrl: String
    ) : LatestProductDomain {
        override fun <T> mapToUi(mapper: LatestProductDomainToUiMapper<T>) =
            mapper.mapToUi(category, name, price, imageUrl)
    }
}