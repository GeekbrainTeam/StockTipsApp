package com.amk.stocktipsapp

import android.app.Application
import com.amk.core.di.appModule
import com.amk.core.di.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule))
        }
    }
}