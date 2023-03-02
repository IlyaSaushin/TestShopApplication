package com.earl.shop_presentation.di

import com.earl.shop_presentation.ui.productDetails.ProductDetailsFragment
import com.earl.shop_presentation.ui.mainShopScreen.ShopFragment
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

    fun inject(fragment: ShopFragment)

    fun inject(fragment: ProductDetailsFragment)
}