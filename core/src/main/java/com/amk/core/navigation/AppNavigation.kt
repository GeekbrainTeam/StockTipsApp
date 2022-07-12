package com.amk.core.navigation

import androidx.navigation.NavController

interface AppNavigation {

    fun execute(action: Action, arg: String)
    val navController: NavController
}