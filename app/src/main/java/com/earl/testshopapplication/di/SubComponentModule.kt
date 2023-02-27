package com.earl.testshopapplication.di

import com.earl.auth_presentation.di.AuthComponent
import dagger.Module

@Module(subcomponents = [AuthComponent::class])
class SubComponentModule