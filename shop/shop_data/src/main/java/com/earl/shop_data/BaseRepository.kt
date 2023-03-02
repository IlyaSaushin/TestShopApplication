package com.earl.shop_data

import android.util.Log
import com.earl.shop_data.mappers.*
import com.earl.shop_data.models.FlashSaleProductData
import com.earl.shop_data.models.LatestProductData
import com.earl.shop_data.models.ProductDetailsData
import com.earl.shop_domain.Repository
import com.earl.shop_domain.models.FlashSaleProductDomain
import com.earl.shop_domain.models.LatestProductDomain
import com.earl.shop_domain.models.ProductDetailsDomain
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
        Log.d("tag", "fetchFlashSaleProducts: $e")
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
        Log.d("tag", "fetchLatestProducts: $e")
        e.printStackTrace()
        emptyList()
    }

    override suspend fun fetchProductDetails() =
        jsonParseHelper.parseToProductDetails(networkService.fetchProductDetails().string())
            .mapToData(productDetailsRemoteToDataMapper)
            .mapToDomain(productDetailsDataToDomainMapper)
}