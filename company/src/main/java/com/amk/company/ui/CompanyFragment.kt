package com.amk.company.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.amk.company.R
import com.amk.company.databinding.FragmentCompanyBinding
import com.amk.company.ui.candlechart.CandlestickViewImpl
import com.amk.company.viewmodel.CompanyViewModel
import com.amk.core.repository.Repository
import com.amk.core.ui.BaseFragment
import org.koin.android.ext.android.inject

class CompanyFragment : BaseFragment<FragmentCompanyBinding>() {
    override fun getViewBinding() = FragmentCompanyBinding.inflate(layoutInflater)

    private val repository: Repository by inject()
    private lateinit var viewModel: CompanyViewModel
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
        viewModel = ViewModelProvider(requireActivity())[CompanyViewModel::class.java]
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
