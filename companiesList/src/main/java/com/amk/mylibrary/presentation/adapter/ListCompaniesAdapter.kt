package com.amk.mylibrary.presentation.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.Company
import com.amk.mylibrary.databinding.ItemCompanyBinding
import com.amk.mylibrary.presentation.CompaniesListViewModel


class ListCompaniesAdapter(
    private val list: List<Company>,
    private val onClickListener: OnStateClickListener,
    private val viewModel: CompaniesListViewModel
) :
    RecyclerView.Adapter<ListCompaniesHolder>() {

    interface OnStateClickListener {
        fun onStateClick(company: Company, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCompaniesHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListCompaniesHolder(ItemCompanyBinding.inflate(inflater, parent, false), viewModel)
    }

    override fun onBindViewHolder(holder: ListCompaniesHolder, position: Int) {
        val company: Company = list[position]
        holder.bind(company)
        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(company, position)
        }
    }

    override fun getItemCount(): Int = list.size
}

