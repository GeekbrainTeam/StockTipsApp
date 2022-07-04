package com.amk.stocktipsapp

import androidx.navigation.NavController
import com.google.android.material.bottomappbar.BottomAppBar

fun setSupportActionBarOneItem(toolbarMain: BottomAppBar,
                               navController:NavController,
                               idItem1: Int,
                               idAction1: Int
) {
    toolbarMain.setOnMenuItemClickListener {
        when (it.itemId) {
            idItem1 -> {
                navController.navigate(idAction1)
                true
            }
            else -> false
        }
    }
}
fun setSupportActionBarTwoItem(toolbarMain: BottomAppBar,
                               navController:NavController,
                               idItem1: Int,
                               idAction1: Int,
                               idItem2: Int,
                               idAction2: Int
) {
    toolbarMain.setOnMenuItemClickListener {
        when (it.itemId) {
            idItem1 -> {
                navController.navigate(idAction1)
                true
            }
            idItem2 -> {
                navController.navigate(idAction2)
                true
            }
            else -> false
        }
    }
}