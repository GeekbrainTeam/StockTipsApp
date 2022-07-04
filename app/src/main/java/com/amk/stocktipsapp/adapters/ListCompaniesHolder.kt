package com.amk.stocktipsapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.amk.stocktipsapp.databinding.ItemCompanyBinding
import com.amk.stocktipsapp.model.FakeModel

class ListCompaniesHolder(private val binding: ItemCompanyBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(commonModel: FakeModel) {
        if (binding.checkBoxFavorite.isChecked) {
            binding.briefNameCompany.text = "фаворит"
        } else if (!binding.checkBoxFavorite.isChecked) {
            binding.briefNameCompany.text = " не фаворит"
        }
        binding.nameCompany.text = commonModel.fakeName
        binding.nominalPrice.text = commonModel.fakeTotal.toString()
    }


}

/*
class ListCompaniesHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_company, parent, false)) {

    private val nameCompany: TextView = itemView.findViewById (R.id.name_company)
    private val nominalPrice: TextView = itemView.findViewById (R.id.nominal_price)
    private var checkBoxFavorite: CheckBox = itemView.findViewById(R.id.checkBox_favorite)


    fun bind(commonModel: FakeModel) {
        nameCompany.text = commonModel.fakeName
        nominalPrice.text = commonModel.fakeTotal.toString()

    }

}*/
