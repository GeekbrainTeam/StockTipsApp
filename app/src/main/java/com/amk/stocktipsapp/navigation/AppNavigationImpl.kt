package com.amk.stocktipsapp.navigation

import android.app.Activity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.amk.core.navigation.Action
import com.amk.core.navigation.AppNavigation
import com.amk.stocktipsapp.R

class AppNavigationImpl(
    activity: Activity
) : AppNavigation {

    override val navController: NavController =
        findNavController(activity, R.id.fragment_container_view)

    override fun execute(action: Action, arg: String) {
        when (action) {
            Action.ListCompanyToCompany -> {
                val bundle = Bundle()
                bundle.putString("SECID", arg)
                navController.navigate(R.id.company, bundle)
            }
        }
    }
}
