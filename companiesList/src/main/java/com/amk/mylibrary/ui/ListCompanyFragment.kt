package com.amk.mylibrary.ui

import android.os.Bundle
import android.view.View
import com.amk.core.navigation.AppNavigation
import com.amk.core.ui.BaseFragment
import com.amk.mylibrary.databinding.FragmentListCompanyBinding
import com.amk.mylibrary.interactors.StatesCompanyListInteractor
import com.amk.mylibrary.presentation.CompaniesListViewModel
import com.amk.mylibrary.utils.*
import org.koin.android.ext.android.inject

class ListCompanyFragment : BaseFragment<FragmentListCompanyBinding, CompaniesListViewModel>() {

    override fun getViewBinding() = FragmentListCompanyBinding.inflate(layoutInflater)
    override fun getVModelClass() = CompaniesListViewModel::class.java

    private val coordinator: AppNavigation by inject()
    private var typeSort: TypeSort = DEFAULT_TYPE_SORT
    private var directionSort: Direction = DEFAULT_DIRECTION_SORT
    private var firstElements: FavoriteState = DEFAULT_FIRST
    private val statesCompanyListInteractor: StatesCompanyListInteractor by lazy {
        StatesCompanyListInteractor(binding, coordinator, viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.setFragmentResultListener(KEY, this) { _, bundle ->
            typeSort = bundle.get(TYPE_OF_SORT) as TypeSort
            directionSort = bundle.get(DIRECTION_OF_SORT) as Direction
            viewModel.chooseSort(directionSort, typeSort, firstElements)
        }
        viewModel.companiesData.observe(viewLifecycleOwner) {
            statesCompanyListInteractor.init(it)
        }

        binding.bottomSortCompany.setOnClickListener {
            val dialog = DialogSorting.getInstance()
            dialog.show(childFragmentManager, ARGUMENT_KEY)
        }
        binding.bottomFilterCompany.setOnClickListener {
            if (firstElements == DEFAULT_FIRST) {
                firstElements = FavoriteState.FavoriteUp
                binding.bottomFilterCompany.setIconResource(com.amk.core.R.drawable.ic_baseline_star_24)
            } else {
                firstElements = FavoriteState.FavoriteMix
                binding.bottomFilterCompany.setIconResource(com.amk.core.R.drawable.ic_baseline_star_outline_24)
            }
            viewModel.chooseSort(directionSort, typeSort, firstElements)
        }
    }

}