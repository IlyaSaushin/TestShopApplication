package com.earl.testshopapplication.di

import android.app.Application
import com.earl.auth_presentation.di.AuthComponent
import com.earl.profile_presentation.di.ProfileComponent
import com.earl.shop_presentation.di.ShopComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MainModule::class,
        SubComponentModule::class,
        DatabaseModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application) : AppComponent
    }

    fun authComponent() : AuthComponent.Factory

    fun profileComponent() : ProfileComponent.Factory

    fun shopComponent() : ShopComponent.Factory
}