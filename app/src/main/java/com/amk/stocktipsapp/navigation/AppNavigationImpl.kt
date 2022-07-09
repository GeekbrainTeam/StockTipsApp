package com.amk.stocktipsapp.navigation

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.amk.core.navigation.Action
import com.amk.core.navigation.AppNavigation
import com.amk.stocktipsapp.R

class AppNavigationImpl(
    private val activity: Activity
) : AppNavigation {

    override val navController: NavController =
        findNavController(activity, R.id.fragment_container_view)

    override fun execute(action: Action) {
        when (action) {
            Action.ListCompanyToCompany -> {
                navController.navigate(R.id.action_go_to_home_to_company)
            }
        }
    }
}
