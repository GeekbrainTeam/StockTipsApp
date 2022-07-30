package com.amk.mylibrary.interactors

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RecyclerViewState(
    private val binding: FragmentListCompanyBinding,
    private val coordinator: AppNavigation,
    private val viewModel: CompaniesListViewModel,
    context: Context
) : ListCompaniesAdapter.OnStateCheckBoxListener {

    private val recyclerView: RecyclerView = binding.recyclerViewCompanies
    private val stateClickListener: ListCompaniesAdapter.OnStateClickListener =
        object : ListCompaniesAdapter.OnStateClickListener {
            override fun onStateClick(company: Company, position: Int) {
                coordinator.execute(Action.ListCompanyToCompany, company.entityCompany.secId)
            }
        }
    private val adapter = ListCompaniesAdapter(stateClickListener, /*viewModel*/this, context)
    private var position: Int = 0
    private var statePosition: StatePosition = StatePosition.MoveToOffset
    var isShowFab = true

    init {
        recyclerView.layoutManager = LinearLayoutManager(
            binding.root.context,
            LinearLayoutManager.VERTICAL, false
        )
        recyclerView.adapter = ListCompaniesAdapter(stateClickListener, /*viewModel*/this, context)
        position = recyclerView.computeVerticalScrollOffset()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (isShowFab && dy <= 0 && binding.bottomFilterCompany.visibility == View.GONE) {
                    show(binding.bottomFilterCompany)
                    show(binding.bottomSortCompany)
                } else if (isShowFab && dy > 0 && binding.bottomFilterCompany.visibility == View.VISIBLE) {
                    hide(binding.bottomFilterCompany)
                    hide(binding.bottomSortCompany)
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    SCROLL_STATE_IDLE -> {
                        position = recyclerView.computeVerticalScrollOffset()
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
        adapter.submitList(newList = list)
        recyclerView.adapter = adapter
        setStatePosition(size = list.size)
    }

    private fun setStatePosition(size: Int) {
        when (statePosition) {
            StatePosition.MoveToOffset -> {
                isShowFab = false
                recyclerView.scrollBy(0, position)
                isShowFab = true
            }
            StatePosition.MoveToUp -> {
                viewModel.viewModelScope.launch(Dispatchers.Main) {
                    delay(200)
                    if (size > 1) {
                        recyclerView.layoutManager?.scrollToPosition(0)
                    }
                }
            }
            StatePosition.NotMove -> {}
        }
    }

    private fun hide(fab: ExtendedFloatingActionButton) {
        fab.startAnimation(toBottomAnimation)
        fab.startAnimation(toBottomAnimation)
        binding.bottomFilterCompany.visibility = View.GONE
        binding.bottomSortCompany.visibility = View.GONE
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

    override fun onCheckedChanged(company: Company, isChecked: Boolean) {
        statePosition = StatePosition.MoveToOffset
        viewModel.changeStatusFavorite(company.entityCompany.secId)
    }

    fun setStatePosition(statePosition: StatePosition) {
        this.statePosition = statePosition
    }

}

sealed interface StatePosition {
    object MoveToUp : StatePosition
    object MoveToOffset : StatePosition
    object NotMove : StatePosition
}