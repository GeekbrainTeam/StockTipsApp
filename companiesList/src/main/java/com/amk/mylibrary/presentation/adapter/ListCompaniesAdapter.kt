package com.amk.mylibrary.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.Company
import com.amk.mylibrary.databinding.ItemCompanyBinding


class ListCompaniesAdapter(
    private val onClickListener: OnStateClickListener,
    private val onStateCheckBoxListener: OnStateCheckBoxListener
    //private val viewModel: CompaniesListViewModel
) : RecyclerView.Adapter<ListCompaniesHolder>() {

    private val diffUtil = AsyncListDiffer(this, DIFF_CALLBACK)

    interface OnStateClickListener {
        fun onStateClick(company: Company, position: Int)
    }

    interface OnStateCheckBoxListener {
        fun onCheckedChanged(company: Company, isChecked: Boolean)
    }

    fun submitList(newList: List<Company>) {
        diffUtil.submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCompaniesHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListCompaniesHolder(
            ItemCompanyBinding.inflate(
                inflater,
                parent,
                false
            ), /*viewModel*/
        )
    }

    override fun onBindViewHolder(holder: ListCompaniesHolder, position: Int) {
        val company: Company = diffUtil.currentList[position]
        holder.bind(company)
        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(company, position)
        }
        holder.onCheck.setOnClickListener {
            onStateCheckBoxListener.onCheckedChanged(company, company.favorite)
        }
    }

    override fun getItemCount(): Int = diffUtil.currentList.size

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<Company> =
            object : DiffUtil.ItemCallback<Company>() {

                override fun areItemsTheSame(
                    oldItem: Company,
                    newItem: Company
                ): Boolean =
                    oldItem.entityCompany.secId == newItem.entityCompany.secId &&
                            oldItem.favorite == newItem.favorite

                override fun areContentsTheSame(
                    oldItem: Company,
                    newItem: Company
                ): Boolean =
                    oldItem.entityCompany == newItem.entityCompany &&
                            oldItem.favorite == newItem.favorite
            }
    }


}

