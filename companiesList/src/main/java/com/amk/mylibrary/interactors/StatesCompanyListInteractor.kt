package com.amk.mylibrary.interactors

import android.widget.Toast
import com.amk.core.navigation.AppNavigation
import com.amk.mylibrary.databinding.FragmentListCompanyBinding

class StatesCompanyListInteractor(
    private val binding: FragmentListCompanyBinding,
    private val state: ListCompanyFragmentState,
    coordinator: AppNavigation,
) {

    private val recyclerViewState = RecyclerViewState(binding, coordinator)
    fun init() {
        when (state) {
            is ListCompanyFragmentState.Loading -> {
                recyclerViewState.loading()
            }
            is ListCompanyFragmentState.Failure -> {
                recyclerViewState.error()
                Toast.makeText(binding.root.context, state.toString(), Toast.LENGTH_LONG).show()
            }
            is ListCompanyFragmentState.Success -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByName -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByNameReverse -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByPrice -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByPriceReverse -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByChangePrice -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByChangePriceReverse -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByChangePercent -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByChangePercentReverse -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            else -> {
                ListCompanyFragmentState.Empty
            }
        }
    }


}