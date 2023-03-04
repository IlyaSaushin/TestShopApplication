package com.earl.shop_presentation.di

import com.earl.shop_data.BaseRepository
import com.earl.shop_data.JsonParseHelper
import com.earl.shop_data.mappers.*
import com.earl.shop_data.models.FlashSaleProductData
import com.earl.shop_data.models.LatestProductData
import com.earl.shop_data.models.ProductDetailsData
import com.earl.shop_domain.Interactor
import com.earl.shop_domain.Repository
import com.earl.shop_domain.models.FlashSaleProductDomain
import com.earl.shop_domain.models.LatestProductDomain
import com.earl.shop_domain.models.ProductDetailsDomain
import com.earl.utils.remoteDataSource.NetworkService
import dagger.Module
import dagger.Provides

@Module
class ShopMainModule {

    @ShopScope
    @Provides
    fun provideInteractor(
        repository: Repository
    ) : Interactor {
        return Interactor.Base(
            repository
        )
    }

    @ShopScope
    @Provides
    fun provideRepository(
        networkService: NetworkService,
        jsonParseHelper: JsonParseHelper,
        flashSaleProductRemoteToDataMapper: FlashSaleProductRemoteToDataMapper<FlashSaleProductData>,
        flashSaleProductDataToDomainMapper: FlashSaleProductDataToDomainMapper<FlashSaleProductDomain>,
        latestProductRemoteToDataMapper: LatestProductRemoteToDataMapper<LatestProductData>,
        latestProductDataToDomainMapper: LatestProductDataToDomainMapper<LatestProductDomain>,
        productDetailsRemoteToDataMapper: ProductDetailsRemoteToDataMapper<ProductDetailsData>,
        productDetailsDataToDomainMapper: ProductDetailsDataToDomainMapper<ProductDetailsDomain>
    ) : Repository {
        return BaseRepository(
            networkService,
            jsonParseHelper,
            flashSaleProductRemoteToDataMapper,
            flashSaleProductDataToDomainMapper,
            latestProductRemoteToDataMapper,
            latestProductDataToDomainMapper,
            productDetailsRemoteToDataMapper,
            productDetailsDataToDomainMapper
        )
    }

    @ShopScope
    @Provides
    fun provideJsonParseHelper() : JsonParseHelper {
        return JsonParseHelper.Base()
    }
}