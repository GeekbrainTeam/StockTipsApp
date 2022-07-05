package com.amk.stocktipsapp.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amk.stocktipsapp.R
import com.amk.stocktipsapp.adapters.ListCompaniesAdapter
import com.amk.stocktipsapp.databinding.FragmentListCompanyBinding
import com.amk.stocktipsapp.model.DialogSorting
import com.amk.stocktipsapp.model.FakeModel


class ListCompanyFragment : Fragment() {
    private var _binding: FragmentListCompanyBinding? = null
    private val binding get() = _binding!!
    private val fakeList = mutableListOf<FakeModel>()
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
        initData()
        setRecyclerView(fakeList)
        binding.bottomSortCompany.setOnClickListener {
            val dialog = DialogSorting()

            dialog.show(childFragmentManager, "ok")
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView(list: List<FakeModel>) {
        val recyclerView: RecyclerView = binding.recyclerViewCompanies
        recyclerView.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
        val stateClickListener: ListCompaniesAdapter.OnStateClickListener =
            object : ListCompaniesAdapter.OnStateClickListener {
                override fun onStateClick(commonModel: FakeModel, position: Int) {
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
    private fun initData () {
        fakeList.add(FakeModel("ddddd1", 1.0, false))
        fakeList.add(FakeModel("ddddd2", 1.0, true))
        fakeList.add(FakeModel("ddddd3", 1.0, false))
        fakeList.add(FakeModel("ddddd4", 1.0, true))
        fakeList.add(FakeModel("ddddd5", 1.0, false))
        fakeList.add(FakeModel("ddddd6", 1.0, true))
        fakeList.add(FakeModel("ddddd7", 1.0, false))
        fakeList.add(FakeModel("ddddd8", 1.0, false))
        fakeList.add(FakeModel("ddddd9", 1.0, false))
        fakeList.add(FakeModel("ddddd10", 1.0, false))
        fakeList.add(FakeModel("ddddd11", 1.0, false))
    }

}