package com.amk.core.navigation

import androidx.navigation.NavController

interface AppNavigation {

    fun execute(action: Action)
    val navController: NavController
}