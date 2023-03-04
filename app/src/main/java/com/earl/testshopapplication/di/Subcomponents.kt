package com.earl.testshopapplication.di

import com.earl.authorization_feature.di.AuthComponentProvider
import com.earl.profile_feature.di.ProfileComponentProvider
import com.earl.shop_feature.di.ShopComponentProvider
import com.earl.testshopapplication.DiProvider

interface Subcomponents : AuthComponentProvider,
    ProfileComponentProvider, ShopComponentProvider {

    override fun provideAuthComponent() = DiProvider.appComponent().authComponent().create()

    override fun provideProfileComponent() = DiProvider.appComponent().profileComponent().create()

    override fun provideShopComponent() = DiProvider.appComponent().shopComponent().create()
}