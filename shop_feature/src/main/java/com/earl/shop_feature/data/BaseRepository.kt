package com.earl.shop_feature.data

import com.earl.shop_feature.data.mappers.*
import com.earl.shop_feature.data.models.FlashSaleProductData
import com.earl.shop_feature.data.models.LatestProductData
import com.earl.shop_feature.data.models.ProductDetailsData
import com.earl.shop_feature.domain.Repository
import com.earl.shop_feature.domain.models.FlashSaleProductDomain
import com.earl.shop_feature.domain.models.LatestProductDomain
import com.earl.shop_feature.domain.models.ProductDetailsDomain
import com.earl.utils.remoteDataSource.NetworkService
import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val networkService: NetworkService,
    private val jsonParseHelper: JsonParseHelper,
    private val flashSaleProductRemoteToDataMapper: FlashSaleProductRemoteToDataMapper<FlashSaleProductData>,
    private val flashSaleProductDataToDomainMapper: FlashSaleProductDataToDomainMapper<FlashSaleProductDomain>,
    private val latestProductRemoteToDataMapper: LatestProductRemoteToDataMapper<LatestProductData>,
    private val latestProductDataToDomainMapper: LatestProductDataToDomainMapper<LatestProductDomain>,
    private val productDetailsRemoteToDataMapper: ProductDetailsRemoteToDataMapper<ProductDetailsData>,
    private val productDetailsDataToDomainMapper: ProductDetailsDataToDomainMapper<ProductDetailsDomain>
) : Repository {


    override suspend fun fetchFlashSaleProducts() =  try {
        jsonParseHelper.parseToFlashSaleProduct(
            networkService.fetchFlashSaleProducts().string()
        )
            .map { it.mapToData(flashSaleProductRemoteToDataMapper) }
            .map { it.mapToDomain(flashSaleProductDataToDomainMapper) }
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }


    override suspend fun fetchLatestProducts() = try {
        jsonParseHelper.parseToLatestProduct(
            networkService.fetchLatestProducts().string()
        )
            .map { it.mapToData(latestProductRemoteToDataMapper) }
            .map { it.mapToDomain(latestProductDataToDomainMapper) }
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }

    override suspend fun fetchProductDetails() =
        jsonParseHelper.parseToProductDetails(networkService.fetchProductDetails().string())
            .mapToData(productDetailsRemoteToDataMapper)
            .mapToDomain(productDetailsDataToDomainMapper)

    override suspend fun fetchBrandsList() =
        jsonParseHelper.parseToBrandsList(
            networkService.fetchProductsBrands().string()
        )
}