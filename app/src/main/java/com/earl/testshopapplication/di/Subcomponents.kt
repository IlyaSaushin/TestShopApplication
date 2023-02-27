package com.earl.testshopapplication.di

import com.earl.auth_presentation.di.AuthComponentProvider
import com.earl.testshopapplication.DiProvider

interface Subcomponents : AuthComponentProvider {

    override fun provideAuthComponent() = DiProvider.appComponent().authComponent().create()
}