package com.amk.mylibrary.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.os.bundleOf
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
        StatesCompanyListInteractor(binding, coordinator, viewModel, requireActivity())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSettingsOfSort()

        childFragmentManager.setFragmentResultListener(KEY, this) { _, bundle ->
            typeSort = bundle.get(TYPE_OF_SORT) as TypeSort
            directionSort = bundle.get(DIRECTION_OF_SORT) as Direction
            viewModel.chooseSort(directionSort, typeSort, firstElements)
        }
        viewModel.companiesData.observe(viewLifecycleOwner) {
            statesCompanyListInteractor.init(it)
        }

        binding.bottomSortCompany.setOnClickListener {
            showDialogSortCompany()
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
        binding.searchInputText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.filteredList(s)
            }
        })
    }

    private fun showDialogSortCompany() {
        val dialog = DialogSorting.getInstance()
        val bundle = bundleOf(TYPE_OF_SORT to typeSort, DIRECTION_OF_SORT to directionSort)
        dialog.arguments = bundle
        dialog.show(childFragmentManager, ARGUMENT_KEY)
    }

    private fun getSettingsOfSort() {
        val sharedPrefsSort = requireContext().getSharedPreferences(TYPE_SORT, Context.MODE_PRIVATE)
        val sharedPrefsDirection =
            requireContext().getSharedPreferences(TYPE_DIRECTION, Context.MODE_PRIVATE)
        val sharedPrefsFavorite =
            requireContext().getSharedPreferences(TYPE_FAVORITE, Context.MODE_PRIVATE)
        val sort = sharedPrefsSort.getInt(TYPE_SORT, 0)
        val direction = sharedPrefsDirection.getInt(TYPE_DIRECTION, 0)
        val favorite = sharedPrefsFavorite.getInt(TYPE_FAVORITE, 0)
        when (sort) {
            0 -> typeSort = DEFAULT_TYPE_SORT
            1 -> typeSort = TypeSort.Name
            2 -> typeSort = TypeSort.Price
            3 -> typeSort = TypeSort.ChangePrice
            4 -> typeSort = TypeSort.Percent
        }
        when (direction) {
            0 -> directionSort = DEFAULT_DIRECTION_SORT
            1 -> directionSort = Direction.Up
            2 -> directionSort = Direction.Down
        }
        when (favorite) {
            0 -> firstElements = DEFAULT_FIRST
            1 -> firstElements = FavoriteState.FavoriteUp
            2 -> firstElements = FavoriteState.FavoriteMix
        }
        viewModel.chooseSort(directionSort, typeSort, firstElements)

    }
}