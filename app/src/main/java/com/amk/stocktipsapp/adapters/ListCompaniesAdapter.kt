package com.amk.stocktipsapp.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.Company


class ListCompaniesAdapter(
    private val list: MutableList<Company>,
    private val onClickListener: OnStateClickListener,

    ) :
    RecyclerView.Adapter<ListCompaniesHolder>() {

    interface OnStateClickListener {
        fun onStateClick(commonModel: Company, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCompaniesHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListCompaniesHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ListCompaniesHolder, position: Int) {
        val commonModel: Company = list[position]
        holder.bind(commonModel)
        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(commonModel, position)
        }



    }

    override fun getItemCount(): Int = list.size
}

