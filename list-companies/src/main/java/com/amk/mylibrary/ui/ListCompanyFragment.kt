package com.amk.mylibrary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.Company
import com.amk.core.navigation.Action
import com.amk.core.navigation.AppNavigation
import com.amk.core.repository.Repository
import com.amk.mylibrary.databinding.FragmentListCompanyBinding
import org.koin.android.ext.android.inject

class ListCompanyFragment : Fragment(), com.amk.core.repository.View {
    private var _binding: FragmentListCompanyBinding? = null
    private val binding get() = _binding!!
    private val repository: Repository by inject()
    private var companiesList = mutableListOf<Company>()
    private val coordinator: AppNavigation by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListCompanyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository.setView(this)
        repository.getCompanies()
        binding.bottomSortCompany.setOnClickListener {
            val dialog = com.amk.mylibrary.model.DialogSorting()

            dialog.show(childFragmentManager, "ok")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView(list: List<Company>) {
        val recyclerView: RecyclerView = binding.recyclerViewCompanies
        recyclerView.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
        val stateClickListener: com.amk.mylibrary.presentation.adapter.ListCompaniesAdapter.OnStateClickListener =
            object : com.amk.mylibrary.presentation.adapter.ListCompaniesAdapter.OnStateClickListener {
                override fun onStateClick(company: Company, position: Int) {
                    coordinator.execute(Action.ListCompanyToCompany)
                }
            }

        recyclerView.adapter =
            com.amk.mylibrary.presentation.adapter.ListCompaniesAdapter(list, stateClickListener)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0 && !binding.bottomSortCompany.isShown) {
                    binding.bottomSortCompany.show()
                    binding.bottomFilterCompany.show()
                } else if (dy > 0 && binding.bottomSortCompany.isShown) {
                    binding.bottomSortCompany.hide()
                    binding.bottomFilterCompany.hide()
                }
            }
        })
    }

    override fun showResult(result: MutableList<Company>) {
        companiesList = result
        setRecyclerView(companiesList)
    }

    override fun showError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }
}
