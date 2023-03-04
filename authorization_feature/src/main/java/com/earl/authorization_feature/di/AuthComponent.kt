package com.earl.authorization_feature.di

import dagger.Subcomponent

@AuthScope
@Subcomponent(
    modules = [
        AuthorizationMainModule::class,
        AuthorizationMappersModule::class
    ]
)
interface AuthComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create() : AuthComponent
    }

    fun inject(fragment: com.earl.authorization_feature.presentation.ui.signIn.SignInFragment)

    fun inject(fragment: com.earl.authorization_feature.presentation.ui.login.LogInFragment)
}