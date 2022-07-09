package com.amk.stocktipsapp.navigation

import android.app.Activity
import com.amk.core.navigation.AppNavigation
import org.koin.dsl.module

val appNavigationModule = module {

    single<AppNavigation> { (activity: Activity) ->
        AppNavigationImpl(activity)
    }
}

