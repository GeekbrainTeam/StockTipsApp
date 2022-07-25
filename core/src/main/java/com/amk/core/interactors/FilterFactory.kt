package com.amk.core.interactors

import com.amk.core.entity.Company

class FilterFactory(private val sourceCompanies: List<Company>) {
    fun searchFilter(charSequence: CharSequence?): List<Company> {
        val result = mutableListOf<Company>()
        if (charSequence==null || charSequence.isEmpty()) {
            result.addAll(sourceCompanies)
        } else {
            result.addAll(sourceCompanies.filter { it.shortName.contains(charSequence) || it.entityCompany.secId.contains(charSequence) })
        }
        return result
    }
}