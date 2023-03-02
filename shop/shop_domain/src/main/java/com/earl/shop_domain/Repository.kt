package com.earl.shop_domain

import com.earl.shop_domain.models.FlashSaleProductDomain
import com.earl.shop_domain.models.LatestProductDomain
import com.earl.shop_domain.models.ProductDetailsDomain

interface Repository {

    suspend fun fetchFlashSaleProducts() : List<FlashSaleProductDomain>

    suspend fun fetchLatestProducts() : List<LatestProductDomain>

    suspend fun fetchProductDetails() : ProductDetailsDomain
}