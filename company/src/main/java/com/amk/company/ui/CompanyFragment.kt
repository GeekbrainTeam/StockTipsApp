package com.amk.company.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.amk.company.databinding.FragmentCompanyBinding
import com.amk.company.viewmodel.CompanyViewModel
import com.amk.core.entity.Company
import com.amk.core.repository.Repository
import org.koin.android.ext.android.inject

class CompanyFragment : Fragment() {
    private var _binding: FragmentCompanyBinding? = null
    private val binding get() = _binding!!
    private val repository: Repository by inject()
    private lateinit var viewModel: CompanyViewModel
    private var candlesList = mutableListOf<Company>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompanyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val secId = this.arguments?.getString("SECID")
        viewModel = ViewModelProvider(requireActivity())[CompanyViewModel::class.java]
        viewModel.setRepo(repository)
        viewModel.candlesListData.observe(viewLifecycleOwner) { company ->
            candlesList = company as MutableList<Company>
            val data = StringBuilder("SECID = $secId SIZE = ${candlesList.size}\n")
            candlesList.forEach {
                data.append("${it.shortName} ${it.tradeDate} ${it.open} ${it.close}\n")
            }
            binding.textView.text = data
        }
        viewModel.errorData.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
        viewModel.getCompanyCandles(secId ?: "")
    }
}
