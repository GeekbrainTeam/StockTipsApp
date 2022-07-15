package com.amk.company.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.amk.company.R
import com.amk.company.databinding.FragmentCompanyBinding
import com.amk.company.presentation.CompanyViewModel
import com.amk.company.ui.candlechart.CandlestickViewImpl
import com.amk.core.ui.BaseFragment

class CompanyFragment : BaseFragment<FragmentCompanyBinding, CompanyViewModel>() {

    override fun getViewBinding() = FragmentCompanyBinding.inflate(layoutInflater)
    override fun getVModelClass() = CompanyViewModel::class.java

    private val candlestickView: CandlestickViewImpl by lazy {
        layoutInflater.inflate(R.layout.view_candlestick, null) as CandlestickViewImpl
    }

    override fun onPause() {
        super.onPause()
        binding.candleSv.visibility = View.INVISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val secId = this.arguments?.getString("SECID")
        binding.candleSv.addView(candlestickView)
        viewModel.candlesListData.observe(viewLifecycleOwner) { companyList ->
            candlestickView.drawCandles(companyList)
            binding.axisYView.drawAxisY(companyList)
            binding.candleSv.post {
                binding.candleSv.scrollBy(binding.candleSv.width, 0)
                binding.candleSv.visibility = View.VISIBLE
            }
        }

        viewModel.errorData.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
        viewModel.getCompanyCandles(secId ?: "")
    }
}
