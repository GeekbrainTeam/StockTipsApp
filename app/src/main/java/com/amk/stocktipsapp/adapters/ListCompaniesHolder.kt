package com.amk.stocktipsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amk.stocktipsapp.R
import com.amk.stocktipsapp.model.FakeModel


class ListCompaniesHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_company, parent, false)) {

    private val nameCompany: TextView = itemView.findViewById (R.id.name_company)
    private val nominalPrice: TextView = itemView.findViewById (R.id.nominal_price)

    private var checkBoxFavorite: CheckBox = itemView.findViewById(R.id.checkBox_favorite)


    fun bind(commonModel: FakeModel) {
        nameCompany.text = commonModel.fakeName
        nominalPrice.text = commonModel.fakeTotal.toString()

    }

}