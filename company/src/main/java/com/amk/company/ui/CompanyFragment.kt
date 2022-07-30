package com.amk.company.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.amk.company.R
import com.amk.company.databinding.FragmentCompanyBinding
import com.amk.company.presentation.CompanyViewModel
import com.amk.company.ui.candlechart.CandlestickViewImpl
import com.amk.company.ui.linechart.LineChart
import com.amk.company.ui.threelinebreak.ThreeLineBreakView
import com.amk.core.entity.EntityCompany
import com.amk.core.ui.BaseFragment
import com.amk.core.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("InflateParams")
class CompanyFragment : BaseFragment<FragmentCompanyBinding, CompanyViewModel>() {

    override fun getViewBinding() = FragmentCompanyBinding.inflate(layoutInflater)
    override fun getVModelClass() = CompanyViewModel::class.java

    private var stateChart: StateChart = StateChart.ShowLine
    private val candlestickView: CandlestickViewImpl by lazy {
        layoutInflater.inflate(R.layout.view_candlestick, null) as CandlestickViewImpl
    }

    private val theeLineBreackView: ThreeLineBreakView by lazy {
        layoutInflater.inflate(R.layout.view_threelinebreak, null) as ThreeLineBreakView
    }

    private val lineChart: LineChart by lazy {
        layoutInflater.inflate(R.layout.view_linechart, null) as LineChart
    }

    override fun onPause() {
        super.onPause()
        binding.candleSv.visibility = View.INVISIBLE
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val secId = this.arguments?.getString("SECID")
        viewModel.getCompanyCandles90(secId ?: "")

        binding.candleSv.addView(lineChart)
        viewModel.candlesListData.observe(viewLifecycleOwner) { companyList ->
            (activity as AppCompatActivity).supportActionBar?.title =
                "${companyList.first().secId}  ${companyList.first().shortName}"
            binding.priceTextview.text = "${companyList.last().close} ₽"
            binding.changePriceTextview.text =
                changePriceAndPercent(companyList.last(), companyList[companyList.size - 2])

            theeLineBreackView.drawThreeLine(companyList)
            candlestickView.drawCandles(companyList)
            lineChart.drawLine(companyList)
            binding.axisYView.drawAxisY(companyList)

            binding.candleSv.post {
                binding.candleSv.scrollBy(binding.candleSv.width, 0)
                binding.candleSv.visibility = View.VISIBLE
            }

            binding.data3Months.setOnClickListener {
                viewModel.getCompanyCandles90(secId ?: "")
            }

            binding.dataHalfYear.setOnClickListener {
                viewModel.getCompanyCandlesHalfYear(secId ?: "")
            }

            binding.dataYear.setOnClickListener {
                viewModel.getCompanyCandlesYear(secId ?: "")
            }

            binding.dataAll.setOnClickListener {
                viewModel.getCompanyCandlesAll(secId ?: "")
            }

            lifecycleScope.launch(Dispatchers.Default) {
                val maxDrop = companyList.calculateMaxPriceDrop() * 100
                var str = "\nМаксимальное падение ${String.format("%.2f", maxDrop)}%"
                val yield = companyList.calculateYield() * 100
                str += "\nДоходность ${String.format("%.2f", yield)}%"
                val volatile = companyList.calculateVolatile() * 100
                str += "\nВолатильность ${String.format("%.2f", volatile)}%"
                val minPrice = companyList.minPrice()
                str += "\nМинимальная цена ${String.format("%.2f", minPrice)}Р"
                val maxPrice = companyList.maxPrice()
                str += "\nМаксимальная цена ${String.format("%.2f", maxPrice)}Р"
                binding.indicatorsTextView.post { binding.indicatorsTextView.text = str }
            }
        }

        binding.descriptionTextView.text = secId?.let { getInfoFromAssets(it, requireActivity()) }
        val drawable = secId?.let { getDrawableFromAssets(it, requireActivity()) }
        binding.logoView.setImageDrawable(drawable)

        viewModel.errorData.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
        binding.changeChartFab.setOnClickListener {
            stateChart = when (stateChart) {
                StateChart.ShowLine -> StateChart.ShowCandles
                StateChart.ShowCandles -> StateChart.ShowThreeLineBreak
                StateChart.ShowThreeLineBreak -> StateChart.ShowLine
            }
            showChart()
        }
    }

    private fun showChart() {
        when (stateChart) {
            StateChart.ShowLine -> {
                binding.changeChartFab.setImageResource(com.amk.core.R.drawable.ic_baseline_filter_list_24)
                binding.candleSv.removeView(theeLineBreackView)
                binding.candleSv.addView(lineChart)
                binding.candleSv.post {
                    binding.candleSv.scrollBy(binding.candleSv.width, 0)
                }
            }
            StateChart.ShowCandles -> {
                theeLineBreackView.visibility = View.GONE
                candlestickView.visibility = View.VISIBLE
                binding.changeChartFab.setImageResource(R.drawable.icon_three_line_break_chart)
                binding.candleSv.removeView(lineChart)
                binding.candleSv.addView(candlestickView)
            }
            StateChart.ShowThreeLineBreak -> {
                candlestickView.visibility = View.GONE
                theeLineBreackView.visibility = View.VISIBLE
                binding.changeChartFab.setImageResource(R.drawable.icon_candlestick_chart)
                binding.candleSv.removeView(candlestickView)
                binding.candleSv.addView(theeLineBreackView)
            }
        }
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

    private sealed interface StateChart {
        object ShowLine : StateChart
        object ShowCandles : StateChart
        object ShowThreeLineBreak : StateChart
    }
}
