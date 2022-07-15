package com.amk.mylibrary.ui

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.Company
import com.amk.core.navigation.Action
import com.amk.core.navigation.AppNavigation
import com.amk.core.ui.BaseFragment
import com.amk.mylibrary.R
import com.amk.mylibrary.databinding.FragmentListCompanyBinding
import com.amk.mylibrary.presentation.adapter.ListCompaniesAdapter
import com.amk.mylibrary.utils.*
import com.amk.mylibrary.viewmodel.CompaniesListViewModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.koin.android.ext.android.inject

class ListCompanyFragment : BaseFragment<FragmentListCompanyBinding, CompaniesListViewModel>() {
    override fun getViewBinding() = FragmentListCompanyBinding.inflate(layoutInflater)
    override fun getVModel() = ViewModelProvider(requireActivity())[CompaniesListViewModel::
    class.java]

    private val coordinator: AppNavigation by inject()
    private var typeSort = ONE_CHOICE
    private var directionSort = DIRECTION_UP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        childFragmentManager.setFragmentResultListener(KEY, this) { requestKey, bundle ->
            typeSort = bundle.getString(TYPE_OF_SORT) ?: DEFAULT_TYPE_SORT
            directionSort = bundle.getString(DIRECTION_OF_SORT) ?: DEFAULT_DIRECTION_SORT
            choiseSort(directionSort, typeSort)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.companiesData.observe(viewLifecycleOwner) {
            val statesCompanyList = StatesCompanyList(binding)
            when (it) {
                is ListCompanyFragmentState.Loading -> {
                    statesCompanyList.loading()
                }
                is ListCompanyFragmentState.Failure -> {
                    statesCompanyList.error()
                    Toast.makeText(activity, it.toString(), Toast.LENGTH_LONG).show()
                }
                is ListCompanyFragmentState.Success -> {
                    statesCompanyList.success()
                    setRecyclerView(it.data)
                }
                is ListCompanyFragmentState.SortByName -> {
                    statesCompanyList.success()
                    setRecyclerView(it.data)
                }
                is ListCompanyFragmentState.SortByNameReverse -> {
                    statesCompanyList.success()
                    setRecyclerView(it.data)
                }
                is ListCompanyFragmentState.SortByPrice -> {
                    statesCompanyList.success()
                    setRecyclerView(it.data)
                }
                is ListCompanyFragmentState.SortByPriceReverse -> {
                    statesCompanyList.success()
                    setRecyclerView(it.data)
                }
                is ListCompanyFragmentState.SortByChangePrice -> {
                    statesCompanyList.success()
                    setRecyclerView(it.data)
                }
                is ListCompanyFragmentState.SortByChangePriceReverse -> {
                    statesCompanyList.success()
                    setRecyclerView(it.data)
                }
                is ListCompanyFragmentState.SortByChangePercent -> {
                    statesCompanyList.success()
                    setRecyclerView(it.data)
                }
                is ListCompanyFragmentState.SortByChangePercentReverse -> {
                    statesCompanyList.success()
                    setRecyclerView(it.data)
                }
                else -> {
                    ListCompanyFragmentState.Empty
                }
            }
        }
        choiseSort(directionSort, typeSort)

        binding.bottomSortCompany.setOnClickListener {
            val dialog = DialogSorting.getInstance()
            dialog.show(childFragmentManager, ARGUMENT_KEY)
        }
    }

    private fun setRecyclerView(list: List<Company>) {
        val recyclerView: RecyclerView = binding.recyclerViewCompanies
        recyclerView.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
        val stateClickListener: ListCompaniesAdapter.OnStateClickListener =
            object : ListCompaniesAdapter.OnStateClickListener {
                override fun onStateClick(company: Company, position: Int) {
                    coordinator.execute(Action.ListCompanyToCompany, company.entityCompany.secId)
                }
            }
        recyclerView.adapter = ListCompaniesAdapter(list, stateClickListener)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy <= 0 && binding.bottomFilterCompany.visibility == View.INVISIBLE) {
                    show(binding.bottomFilterCompany)
                    show(binding.bottomSortCompany)
                } else if (dy > 0 && binding.bottomFilterCompany.visibility == View.VISIBLE) {
                    hide(binding.bottomFilterCompany)
                    hide(binding.bottomSortCompany)
                }
            }
        })
    }

    private fun hide(fab: ExtendedFloatingActionButton) {
        fab.startAnimation(toBottomAnimation)
        fab.startAnimation(toBottomAnimation)
        binding.bottomFilterCompany.visibility = View.INVISIBLE
    }

    private fun show(fab: ExtendedFloatingActionButton) {
        fab.startAnimation(fromBottomAnimation)
        fab.startAnimation(fromBottomAnimation)
        binding.bottomFilterCompany.visibility = View.VISIBLE
    }

    private val fromBottomAnimation: Animation by lazy {
        AnimationUtils.loadAnimation(
            context,
            R.anim.from_bottom_animation
        )
    }

    private val toBottomAnimation: Animation by lazy {
        AnimationUtils.loadAnimation(
            context,
            R.anim.to_bottom_animation
        )
    }

    fun choiseSort(directionSort: String, typeSort: String) {
        if (directionSort == DIRECTION_UP) {
            when (typeSort) {
                ONE_CHOICE -> viewModel.getSortedByName()
                TWO_CHOICE -> viewModel.getSortedByPrice()
                TREE_CHOICE -> viewModel.getSortedByChangePrice()
                FOUR_CHOICE -> viewModel.getSortedByChangePercent()
            }
        } else if (directionSort == DIRECTION_DOWN) {
            when (typeSort) {
                ONE_CHOICE -> viewModel.getSortedByNameReverse()
                TWO_CHOICE -> viewModel.getSortedByPriceReverse()
                TREE_CHOICE -> viewModel.getSortedByChangePriceReverse()
                FOUR_CHOICE -> viewModel.getSortedByChangePercentReverse()
                else -> viewModel.getCompanies()
            }
        }
    }
}