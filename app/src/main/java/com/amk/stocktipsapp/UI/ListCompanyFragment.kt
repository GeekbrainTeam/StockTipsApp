package com.amk.stocktipsapp.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.Company
import com.amk.core.repository.Repository
import com.amk.stocktipsapp.R
import com.amk.stocktipsapp.adapters.ListCompaniesAdapter
import com.amk.stocktipsapp.databinding.FragmentListCompanyBinding
import com.amk.stocktipsapp.model.DialogSorting
import org.koin.android.ext.android.inject

class ListCompanyFragment : Fragment(), com.amk.core.repository.View {
    private var _binding: FragmentListCompanyBinding? = null
    private val binding get() = _binding!!
    private val repository: Repository by inject()
    private var companiesList = mutableListOf<Company>()
    lateinit var navController: NavController

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
        navController = Navigation.findNavController(view)
        repository.setView(this)
        repository.getCompanies()
        binding.bottomSortCompany.setOnClickListener {
            val dialog = DialogSorting()

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
        val stateClickListener: ListCompaniesAdapter.OnStateClickListener =
            object : ListCompaniesAdapter.OnStateClickListener {
                override fun onStateClick(company: Company, position: Int) {
                    navController.navigate(R.id.action_go_to_home_to_company)
                }
            }

        recyclerView.adapter = ListCompaniesAdapter(list, stateClickListener)
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