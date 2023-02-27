package com.earl.testshopapplication.di

import com.earl.auth_presentation.di.AuthComponentProvider
import com.earl.profile_presentation.di.ProfileComponent
import com.earl.profile_presentation.di.ProfileComponentProvider
import com.earl.testshopapplication.DiProvider

interface Subcomponents : AuthComponentProvider, ProfileComponentProvider {

    override fun provideAuthComponent() = DiProvider.appComponent().authComponent().create()

    override fun provideProfileComponent() = DiProvider.appComponent().profileComponent().create()
}