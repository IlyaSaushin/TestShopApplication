package com.earl.testshopapplication

import android.app.Application
import com.earl.testshopapplication.di.DaggerAppComponent

class App : Application() {

    val appComponent = DaggerAppComponent.create()
}