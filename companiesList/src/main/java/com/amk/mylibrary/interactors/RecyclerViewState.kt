package com.amk.mylibrary.interactors

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.amk.core.entity.Company
import com.amk.core.navigation.Action
import com.amk.core.navigation.AppNavigation
import com.amk.mylibrary.R
import com.amk.mylibrary.databinding.FragmentListCompanyBinding
import com.amk.mylibrary.presentation.CompaniesListViewModel
import com.amk.mylibrary.presentation.adapter.ListCompaniesAdapter
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class RecyclerViewState(
    private val binding: FragmentListCompanyBinding,
    private val coordinator: AppNavigation,
    viewModel: CompaniesListViewModel,
) {

    private val recyclerView: RecyclerView = binding.recyclerViewCompanies
    private val stateClickListener: ListCompaniesAdapter.OnStateClickListener =
        object : ListCompaniesAdapter.OnStateClickListener {
            override fun onStateClick(company: Company, position: Int) {
                coordinator.execute(Action.ListCompanyToCompany, company.entityCompany.secId)
            }
        }
    private val adapter = ListCompaniesAdapter(stateClickListener, viewModel)
    private var position: Int = -1

    init {
        recyclerView.layoutManager = LinearLayoutManager(
            binding.root.context,
            LinearLayoutManager.VERTICAL, false
        )
        recyclerView.adapter = ListCompaniesAdapter(stateClickListener, viewModel)
        position =
            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
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

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    SCROLL_STATE_IDLE -> {
                        position =
                            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    }
                }
            }
        })
    }

    internal fun loading() {
        binding.recyclerViewCompanies.isVisible = false
        binding.progressBar.isVisible = true
    }

    internal fun error() {
        binding.recyclerViewCompanies.isVisible = false
        binding.progressBar.isVisible = false
    }

    internal fun success() {
        binding.recyclerViewCompanies.isVisible = true
        binding.progressBar.isVisible = false
    }

    internal fun setRecyclerView(list: List<Company>) {
        adapter.submitList(list)
        recyclerView.adapter = adapter
        recyclerView.scrollToPosition(position)

    }

    private fun hide(fab: ExtendedFloatingActionButton) {
        fab.startAnimation(toBottomAnimation)
        fab.startAnimation(toBottomAnimation)
        binding.bottomFilterCompany.visibility = View.INVISIBLE
        binding.bottomSortCompany.visibility = View.INVISIBLE
    }

    private fun show(fab: ExtendedFloatingActionButton) {
        fab.startAnimation(fromBottomAnimation)
        fab.startAnimation(fromBottomAnimation)
        binding.bottomFilterCompany.visibility = View.VISIBLE
        binding.bottomSortCompany.visibility = View.VISIBLE
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
