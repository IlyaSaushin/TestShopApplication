package com.earl.shop_presentation.di

import com.earl.shop_data.mappers.*
import com.earl.shop_data.models.FlashSaleProductData
import com.earl.shop_data.models.LatestProductData
import com.earl.shop_domain.mappers.FlashSaleProductDomainToUiMapper
import com.earl.shop_domain.mappers.LatestProductDomainToUiMapper
import com.earl.shop_domain.models.FlashSaleProductDomain
import com.earl.shop_domain.models.LatestProductDomain
import com.earl.shop_presentation.ui.mappers.BaseFlashSaleProductDomainToUiMapper
import com.earl.shop_presentation.ui.mappers.BaseLatestProductDomainToUiMapper
import com.earl.shop_presentation.ui.models.FlashSaleProductUi
import com.earl.shop_presentation.ui.models.LatestProductUi
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
    fun provideLatestProductRemoteToDataMapper() : LatestProductRemoteToDataMapper<LatestProductData> {
        return BaseLatestProductRemoteToDataMapper()
    }

    @ShopScope
    @Provides
    fun provideLatestProductDataToDomainMapper() : LatestProductDataToDomainMapper<LatestProductDomain> {
        return BaseLatestProductDataToDomainMapper()
    }

    @ShopScope
    @Provides
    fun provideLatestProductDomainToUiMapper() : LatestProductDomainToUiMapper<LatestProductUi> {
        return BaseLatestProductDomainToUiMapper()
    }
}