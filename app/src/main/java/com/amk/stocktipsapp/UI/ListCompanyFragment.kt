package com.amk.stocktipsapp.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class ListCompanyFragment : Fragment() {
    private var _binding: FragmentListCompanyBinding? = null
    private val binding get() = _binding!!
    private val fakeList = mutableListOf<FakeModel>()
    lateinit var navController: NavController

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
        binding.bottomSortCompany.startAnimation(toBottomAnimation)
        binding.bottomFilterCompany.startAnimation(toBottomAnimation)
        binding.bottomFilterCompany.visibility = View.INVISIBLE
    }

    private fun show(fab: ExtendedFloatingActionButton) {
        binding.bottomSortCompany.startAnimation(fromBottomAnimation)
        binding.bottomFilterCompany.startAnimation(fromBottomAnimation)
        binding.bottomFilterCompany.visibility = View.VISIBLE
    }

    private fun initData() {
        fakeList.add(FakeModel("АбрауДюрсо", "ABRD", 10.0, 12.5, 0.12345, false))
        fakeList.add(FakeModel("Система ао", "ACKO", -1.0, 20.0, 24.15, true))
        fakeList.add(FakeModel("Аэрофлот", "AFLT", 25.0, 33.4, 0.16, false))
        fakeList.add(FakeModel("AGRO-гдр", "AGRO", 14.0, 5.5, 0.4687, true))
        fakeList.add(FakeModel("Акрон", "AKRN", -18.0, 0.36, 45.5, false))
        fakeList.add(FakeModel("АЛРОСА ао", "ALRS", 4.0, 45.5, 1024545.42, true))
        fakeList.add(FakeModel("АшинскийМЗ", "AMEZ", 100.0, 121.12, 454.0, false))
        fakeList.add(FakeModel("Аптеки36и6", "APTK", -25.27, 15.4, 534.47, false))
        fakeList.add(FakeModel("РусАква ао", "AQUA", 0.21464, 0.36, 0.2453, false))
        fakeList.add(FakeModel("Арсагера", "ARSA", 1024.25, 89.7, 453.37, false))
        fakeList.add(FakeModel("АстрЭнСб", "ASSB", -18.453, 0.12, 0.4357, false))
    }

}