package com.earl.shop_domain

import com.earl.shop_domain.models.FlashSaleProductDomain
import com.earl.shop_domain.models.LatestProductDomain
import com.earl.shop_domain.models.ProductDetailsDomain
import javax.inject.Inject

interface Interactor {

    suspend fun fetchFlashSaleProducts() : List<FlashSaleProductDomain>

    suspend fun fetchLatestProducts() : List<LatestProductDomain>

    suspend fun fetchProductDetails() : ProductDetailsDomain

    class Base @Inject constructor(
        private val repository: Repository
    ) : Interactor {

        override suspend fun fetchFlashSaleProducts() =
            repository.fetchFlashSaleProducts()

        override suspend fun fetchLatestProducts() =
            repository.fetchLatestProducts()

        override suspend fun fetchProductDetails() =
            repository.fetchProductDetails()
    }
}