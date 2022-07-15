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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.setFragmentResultListener(KEY, this) { _, bundle ->
            typeSort = bundle.get(TYPE_OF_SORT) as TypeSort
            directionSort = bundle.get(DIRECTION_OF_SORT) as Direction
            chooseSort(directionSort, typeSort)
        }
        viewModel.companiesData.observe(viewLifecycleOwner) {
            val statesCompanyListInteractor = StatesCompanyListInteractor(binding, it, coordinator)
            statesCompanyListInteractor.init()
        }
        chooseSort(directionSort, typeSort)

        binding.bottomSortCompany.setOnClickListener {
            val dialog = DialogSorting.getInstance()
            dialog.show(childFragmentManager, ARGUMENT_KEY)
        }
    }

    private fun chooseSort(directionSort: Direction, typeSort: TypeSort) {
        when (directionSort) {
            Direction.Down -> {
                when (typeSort) {
                    TypeSort.Name -> viewModel.getSortedByName()
                    TypeSort.Price -> viewModel.getSortedByPrice()
                    TypeSort.ChangePrice -> viewModel.getSortedByChangePrice()
                    TypeSort.Percent -> viewModel.getSortedByChangePercent()
                }
            }
            Direction.Up -> {
                when (typeSort) {
                    TypeSort.Name -> viewModel.getSortedByNameReverse()
                    TypeSort.Price -> viewModel.getSortedByPriceReverse()
                    TypeSort.ChangePrice -> viewModel.getSortedByChangePriceReverse()
                    TypeSort.Percent -> viewModel.getSortedByChangePercentReverse()
                }
            }
        }
    }
}