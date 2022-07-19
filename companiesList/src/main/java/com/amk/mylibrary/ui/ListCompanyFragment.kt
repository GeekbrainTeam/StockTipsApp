package com.amk.mylibrary.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.amk.core.navigation.AppNavigation
import com.amk.core.ui.BaseFragment
import com.amk.mylibrary.R
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
    private var firstElements: Filter = DEFAULT_FIRST

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener(KEY_FILTER) {_, bundle ->
            firstElements= bundle.get(FILTER_ORDER) as Filter
        }
        childFragmentManager.setFragmentResultListener(KEY, this) { _, bundle ->
            typeSort = bundle.get(TYPE_OF_SORT) as TypeSort
            directionSort = bundle.get(DIRECTION_OF_SORT) as Direction
            viewModel.chooseSort(directionSort, typeSort, firstElements)
        }
        viewModel.companiesData.observe(viewLifecycleOwner) {
            val statesCompanyListInteractor = StatesCompanyListInteractor(binding, it, coordinator, viewModel)
            statesCompanyListInteractor.init()
        }

        binding.bottomSortCompany.setOnClickListener {
            val dialog = DialogSorting.getInstance()
            dialog.show(childFragmentManager, ARGUMENT_KEY)
        }
        binding.bottomFilterCompany.setOnClickListener {
            if (firstElements ==DEFAULT_FIRST) {
                firstElements=Filter.FirstFavorit
                viewModel.chooseSort(directionSort, typeSort, firstElements)
                binding.bottomFilterCompany.setIconResource(com.amk.core.R.drawable.ic_baseline_star_24)
            } else {
                firstElements = Filter.DefaultFavorite
                viewModel.chooseSort(directionSort, typeSort, firstElements)
                binding.bottomFilterCompany.setIconResource(com.amk.core.R.drawable.ic_baseline_star_outline_24)
            }
            setFragmentResult(
                KEY_FILTER,
                bundleOf(FILTER_ORDER to firstElements)
            )
        }
    }
}