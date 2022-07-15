package com.amk.mylibrary.presentation.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.Company
import com.amk.core.entity.EntityCompany
import com.amk.mylibrary.databinding.ItemCompanyBinding


class ListCompaniesAdapter(
    private val list: List<Company>,
    private val onClickListener: OnStateClickListener
) :
    RecyclerView.Adapter<ListCompaniesHolder>() {

    interface OnStateClickListener {
        fun onStateClick(entityCompany: Company, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCompaniesHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListCompaniesHolder(ItemCompanyBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ListCompaniesHolder, position: Int) {
        val entityCompany: Company = list[position]
        holder.bind(entityCompany)
        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(entityCompany, position)
        }
    }

    override fun getItemCount(): Int = list.size
}

