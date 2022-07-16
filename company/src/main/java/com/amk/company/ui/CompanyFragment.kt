package com.amk.company.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amk.company.R
import com.amk.company.databinding.FragmentCompanyBinding
import com.amk.company.presentation.CompanyViewModel
import com.amk.company.ui.candlechart.CandlestickViewImpl
import com.amk.core.entity.EntityCompany
import com.amk.core.ui.BaseFragment
import com.amk.core.utils.changePrice
import com.amk.core.utils.formatPrice
import com.amk.core.utils.percent

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
            (activity as AppCompatActivity).supportActionBar?.title =
                "${companyList.first().secId}  ${companyList.first().shortName}"
            binding.priceTextview.text = "${companyList.last().close} ₽"
            binding.changePriceTextview.text =
                changePriceAndPercent(companyList.last(), companyList[companyList.size - 2])
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

    private fun changePriceAndPercent(
        company: EntityCompany,
        previousDateCompany: EntityCompany
    ): String {
        val changePrice = company.close.changePrice(previousDateCompany.close)
        val formatChangPrice = formatPrice(company.close)
        val percent = company.close.percent(previousDateCompany.close)
        if (changePrice > 0) {
            binding.changePriceTextview.setTextColor(Color.GREEN)
        } else if (changePrice < 0) {
            binding.changePriceTextview.setTextColor(Color.RED)
        } else {
            binding.changePriceTextview.setTextColor(Color.GRAY)
        }
        return "${String.format("%$formatChangPrice", (changePrice))} ₽  (${
            String.format("%.1f", (percent))
        }%)"
    }


}
