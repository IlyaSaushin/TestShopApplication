package com.earl.testshopapplication.di

import com.earl.authorization_feature.di.AuthComponent
import dagger.Module

@Module(subcomponents = [AuthComponent::class])
class SubComponentModule