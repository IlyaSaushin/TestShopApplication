package com.earl.testshopapplication

import android.app.Application
import com.earl.auth_presentation.di.AuthComponent
import com.earl.auth_presentation.di.AuthComponentProvider
import com.earl.testshopapplication.di.DaggerAppComponent
import com.earl.testshopapplication.di.Subcomponents

class App : Application(), Subcomponents {

    override fun onCreate() {
        super.onCreate()
        DiProvider.buildDi(this)
    }
}