package com.earl.testshopapplication

import android.app.Application
import com.earl.testshopapplication.di.AppComponent
import com.earl.testshopapplication.di.DaggerAppComponent

object DiProvider {

    private lateinit var appComponent: AppComponent

    @JvmStatic
    fun appComponent() = appComponent

    fun buildDi(application: Application) {
        appComponent = DaggerAppComponent.factory().create(application)
    }
}