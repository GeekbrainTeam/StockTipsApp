package com.amk.stocktipsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.Company
import com.amk.core.repository.Repository
import com.amk.stocktipsapp.adapters.ListCompaniesAdapter
import com.amk.stocktipsapp.databinding.FragmentListCompanyBinding
import com.amk.stocktipsapp.model.DialogSorting

class ListCompanyFragment : Fragment(), com.amk.core.repository.View {
    private var _binding: FragmentListCompanyBinding? = null
    private val binding get() = _binding!!
    private val repository = Repository(this)
    private val companiesList = mutableListOf<Company>()

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
        repository.getCompanies()
        //setRecyclerView(companiesList)
        binding.bottomSortCompany.setOnClickListener {
            val dialog = DialogSorting()

            dialog.show(childFragmentManager, "ok")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setRecyclerView(list: MutableList<Company>) {
        val recyclerView: RecyclerView = binding.recyclerViewCompanies
        recyclerView.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
        val stateClickListener: ListCompaniesAdapter.OnStateClickListener =
            object : ListCompaniesAdapter.OnStateClickListener {
                override fun onStateClick(company: Company, position: Int) {
                    Toast.makeText(activity, "Выбрана ${company.secId} копмания", Toast.LENGTH_LONG)
                        .show()
                }
            }

        recyclerView.adapter = ListCompaniesAdapter(list, stateClickListener)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && binding.bottomSortCompany.isShown) {
                    binding.bottomSortCompany.hide()
                    binding.bottomFilterCompany.hide()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.bottomSortCompany.show()
                    binding.bottomFilterCompany.show()
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        })
    }

    override fun showError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }
}