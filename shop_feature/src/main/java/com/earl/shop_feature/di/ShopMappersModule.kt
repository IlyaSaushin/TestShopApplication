package com.earl.shop_feature.di

import com.earl.shop_feature.data.mappers.*
import com.earl.shop_feature.data.models.FlashSaleProductData
import com.earl.shop_feature.domain.mappers.FlashSaleProductDomainToUiMapper
import com.earl.shop_feature.domain.mappers.LatestProductDomainToUiMapper
import com.earl.shop_feature.domain.mappers.ProductDetailsDomainToUiMapper
import com.earl.shop_feature.domain.models.FlashSaleProductDomain
import com.earl.shop_feature.domain.models.ProductDetailsDomain
import com.earl.shop_feature.presentation.mappers.BaseFlashSaleProductDomainToUiMapper
import com.earl.shop_feature.presentation.mappers.BaseLatestProductDomainToUiMapper
import com.earl.shop_feature.presentation.mappers.BaseProductDetailsDomainToUiMapper
import com.earl.shop_feature.presentation.models.FlashSaleProductUi
import com.earl.shop_feature.presentation.models.LatestProductUi
import com.earl.shop_feature.presentation.models.ProductDetailsUi
import dagger.Module
import dagger.Provides

@Module
class ShopMappersModule {

    @ShopScope
    @Provides
    fun provideFlashSaleProductRemoteToDataMapper() : FlashSaleProductRemoteToDataMapper<FlashSaleProductData> {
        return BaseFlashSaleProductRemoteToDataMapper()
    }

    @ShopScope
    @Provides
    fun provideFlashSaleProductDataToDomainMapper() : FlashSaleProductDataToDomainMapper<FlashSaleProductDomain> {
        return BaseFlashSaleProductDataToDomainMapper()
    }

    @ShopScope
    @Provides
    fun provideBaseFlashSaleProductDomainToUiMapper() : FlashSaleProductDomainToUiMapper<FlashSaleProductUi> {
        return BaseFlashSaleProductDomainToUiMapper()
    }

    @ShopScope
    @Provides
    fun provideLatestProductRemoteToDataMapper() : LatestProductRemoteToDataMapper<com.earl.shop_feature.data.models.LatestProductData> {
        return BaseLatestProductRemoteToDataMapper()
    }

    @ShopScope
    @Provides
    fun provideLatestProductDataToDomainMapper() : LatestProductDataToDomainMapper<com.earl.shop_feature.domain.models.LatestProductDomain> {
        return BaseLatestProductDataToDomainMapper()
    }

    @ShopScope
    @Provides
    fun provideLatestProductDomainToUiMapper() : LatestProductDomainToUiMapper<LatestProductUi> {
        return BaseLatestProductDomainToUiMapper()
    }

    @ShopScope
    @Provides
    fun provideProductDetailsRemoteToDataMapper() : ProductDetailsRemoteToDataMapper<com.earl.shop_feature.data.models.ProductDetailsData> {
        return BaseProductDetailsRemoteToDataMapper()
    }

    @ShopScope
    @Provides
    fun provideProductDetailsDataToDomainMapper() : ProductDetailsDataToDomainMapper<ProductDetailsDomain> {
        return BaseProductDetailsDataToDomainMapper()
    }

    @ShopScope
    @Provides
    fun provideProductDetailsDomainToUiMapper() : ProductDetailsDomainToUiMapper<ProductDetailsUi> {
        return BaseProductDetailsDomainToUiMapper()
    }
}