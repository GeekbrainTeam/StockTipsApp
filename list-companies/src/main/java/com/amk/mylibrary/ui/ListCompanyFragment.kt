package com.amk.mylibrary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.db.DataBaseCacheCompany
import com.amk.core.entity.Company
import com.amk.core.navigation.Action
import com.amk.core.navigation.AppNavigation
import com.amk.core.repository.CacheRepository
import com.amk.core.repository.NetworkRepository
import com.amk.core.repository.RepositoryCompany
import com.amk.core.repository.RepositoryCompanyImpl
import com.amk.core.retrofit.MoexApiImpl
import com.amk.mylibrary.R
import com.amk.mylibrary.databinding.FragmentListCompanyBinding
import com.amk.mylibrary.presentation.adapter.ListCompaniesAdapter
import com.amk.mylibrary.viewmodel.CompaniesListViewModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.koin.android.ext.android.inject

class ListCompanyFragment : Fragment() {
    private var _binding: FragmentListCompanyBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: RepositoryCompany
    private lateinit var viewModel: CompaniesListViewModel
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
        val networkRepository = NetworkRepository(MoexApiImpl().getMoexService())
        val database = DataBaseCacheCompany.getDatabase(requireContext())
        val cacheRepository = CacheRepository(database.cacheDao())
        repository = RepositoryCompanyImpl(requireContext(),networkRepository, cacheRepository)
        viewModel = ViewModelProvider(requireActivity())[CompaniesListViewModel::class.java]
        viewModel.setRepo(repository)
        viewModel.companiesListDataYesterday.observe(viewLifecycleOwner) {
            companiesList = it as MutableList<Company>
            setRecyclerView(companiesList)
        }
        viewModel.errorData.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
        viewModel.getCompanies()
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
}