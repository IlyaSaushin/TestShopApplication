package com.earl.shop_feature.domain

import com.earl.shop_feature.domain.models.FlashSaleProductDomain
import com.earl.shop_feature.domain.models.LatestProductDomain
import com.earl.shop_feature.domain.models.ProductDetailsDomain

interface Repository {

    suspend fun fetchFlashSaleProducts() : List<FlashSaleProductDomain>

    suspend fun fetchLatestProducts() : List<LatestProductDomain>

    suspend fun fetchProductDetails() : ProductDetailsDomain

    suspend fun fetchBrandsList() : List<String>
}