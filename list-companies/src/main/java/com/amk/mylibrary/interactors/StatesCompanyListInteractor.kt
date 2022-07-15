package com.amk.mylibrary.interactors

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.Company
import com.amk.core.navigation.Action
import com.amk.core.navigation.AppNavigation
import com.amk.mylibrary.R
import com.amk.mylibrary.databinding.FragmentListCompanyBinding
import com.amk.mylibrary.presentation.adapter.ListCompaniesAdapter
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class StatesCompanyListInteractor(
    private val binding: FragmentListCompanyBinding,
    private val state: ListCompanyFragmentState,
    private val coordinator: AppNavigation,
) {
    fun init() {
        when (state) {
            is ListCompanyFragmentState.Loading -> {
                loading()
            }
            is ListCompanyFragmentState.Failure -> {
                error()
                Toast.makeText(binding.root.context, state.toString(), Toast.LENGTH_LONG).show()
            }
            is ListCompanyFragmentState.Success -> {
                success()
                setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByName -> {
                success()
                setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByNameReverse -> {
                success()
                setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByPrice -> {
                success()
                setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByPriceReverse -> {
                success()
                setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByChangePrice -> {
                success()
                setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByChangePriceReverse -> {
                success()
                setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByChangePercent -> {
                success()
                setRecyclerView(state.data)
            }
            is ListCompanyFragmentState.SortByChangePercentReverse -> {
                success()
                setRecyclerView(state.data)
            }
            else -> {
                ListCompanyFragmentState.Empty
            }
        }
    }


    fun loading() {
        binding.recyclerViewCompanies.isVisible = false
        binding.progressBar.isVisible = true
    }

    fun error() {
        binding.recyclerViewCompanies.isVisible = false
        binding.progressBar.isVisible = false
    }

    fun success() {
        binding.recyclerViewCompanies.isVisible = true
        binding.progressBar.isVisible = false
    }

    private fun setRecyclerView(list: List<Company>) {
        val recyclerView: RecyclerView = binding.recyclerViewCompanies
        recyclerView.layoutManager = LinearLayoutManager(
            binding.root.context,
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
            binding.root.context,
            R.anim.from_bottom_animation
        )
    }

    private val toBottomAnimation: Animation by lazy {
        AnimationUtils.loadAnimation(
            binding.root.context,
            R.anim.to_bottom_animation
        )
    }

}