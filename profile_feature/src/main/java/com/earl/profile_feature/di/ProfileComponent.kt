package com.earl.profile_feature.di

import dagger.Subcomponent

@ProfileScope
@Subcomponent(modules = [
    ProfileMainModule::class
])
interface ProfileComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create() : ProfileComponent
    }

    fun inject(fragment: com.earl.profile_feature.presentation.ProfileFragment)
}