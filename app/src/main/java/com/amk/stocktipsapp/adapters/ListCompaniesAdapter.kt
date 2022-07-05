package com.amk.stocktipsapp.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amk.stocktipsapp.databinding.ItemCompanyBinding
import com.amk.stocktipsapp.model.FakeModel


class ListCompaniesAdapter(
    private val list: List<FakeModel>,
    private val onClickListener: OnStateClickListener
) :
    RecyclerView.Adapter<ListCompaniesHolder>() {

    interface OnStateClickListener {
        fun onStateClick(commonModel: FakeModel, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCompaniesHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListCompaniesHolder(ItemCompanyBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ListCompaniesHolder, position: Int) {
        val commonModel: FakeModel = list[position]
        holder.bind(commonModel)
        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(commonModel, position)
        }
    }

    override fun getItemCount(): Int = list.size
}

