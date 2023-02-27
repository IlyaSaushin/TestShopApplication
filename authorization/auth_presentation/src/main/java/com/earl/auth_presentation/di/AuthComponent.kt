package com.earl.auth_presentation.di

import com.earl.auth_presentation.prensentation.ui.login.LogInFragment
import com.earl.auth_presentation.prensentation.ui.signIn.SignInFragment
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

    fun inject(fragment: SignInFragment)

    fun inject(fragment: LogInFragment)
}