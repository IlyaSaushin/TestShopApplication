package com.earl.shop_feature.di

import com.earl.shop_feature.presentation.screens.mainShopScreen.ShopHomeFragment
import com.earl.shop_feature.presentation.screens.productDetailsScreen.ProductDetailsFragment
import dagger.Subcomponent

@ShopScope
@Subcomponent(
    modules = [
        ShopMainModule::class,
        ShopMappersModule::class
    ]
)
interface ShopComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create() : ShopComponent
    }

    fun inject(fragment: ShopHomeFragment)

    fun inject(fragment: ProductDetailsFragment)
}