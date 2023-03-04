package com.earl.testshopapplication

import android.app.Application
import com.earl.testshopapplication.di.Subcomponents

class App : Application(), Subcomponents {

    override fun onCreate() {
        super.onCreate()
        DiProvider.buildDi(this)
    }
}