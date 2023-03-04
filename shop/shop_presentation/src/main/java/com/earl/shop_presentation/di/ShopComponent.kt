package com.earl.shop_presentation.di

import com.earl.shop_presentation.ui.screens.productDetailsScreen.ProductDetailsFragment
import com.earl.shop_presentation.ui.screens.mainShopScreen.ShopHomeFragment
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