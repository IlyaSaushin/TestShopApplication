package com.earl.profile_presentation.di

import com.earl.profile_presentation.ui.ProfileFragment
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

    fun inject(fragment: ProfileFragment)
}