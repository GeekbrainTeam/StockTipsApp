package com.amk.mylibrary.interactors

import android.widget.Toast
import com.amk.core.navigation.AppNavigation
import com.amk.mylibrary.databinding.FragmentListCompanyBinding
import com.amk.mylibrary.presentation.CompaniesListViewModel

class StatesCompanyListInteractor(
    private val binding: FragmentListCompanyBinding,
    coordinator: AppNavigation,
    viewModel: CompaniesListViewModel,
) {

    private val recyclerViewState = RecyclerViewState(binding, coordinator, viewModel)

    fun init(state: ListCompanyFragmentState) {
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
            is ListCompanyFragmentState.SortFavoriteByName -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortFavoriteByNameReverse -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortFavoriteByPrice -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortFavoriteByPriceReverse -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortFavoriteByChangePrice -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortFavoriteByChangePriceReverse -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortFavoriteByChangePercent -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortFavoriteByChangePercentReverse -> {
                recyclerViewState.success()
                recyclerViewState.setRecyclerView(state.data)
            }
            else -> {
                ListCompanyFragmentState.Empty
            }
        }
    }


}