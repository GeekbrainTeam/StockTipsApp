package com.amk.mylibrary.interactors

import com.amk.core.entity.Company
import com.amk.core.repository.RepositoryCompany
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilterFactory(
    private val repository: RepositoryCompany,
) {
    private val setCompany: MutableSet<Company> = mutableSetOf()

    suspend fun getFilterCompany(charSequence: CharSequence?): Flow<Set<Company>> {
        return repository.createListOneDayYesterday().map { setCompany ->
            this.setCompany.clear()
            this.setCompany.addAll(setCompany)
            filterSet(charSequence.toString())
        }
    }

    fun filter(search: String): Set<Company> = filterSet(search)

    private fun filterSet(search: String): Set<Company> {
        if (search.isBlank()) return setCompany.toSet()
        return setCompany.filter {
            (it.entityCompany.secId.contains(search, true) ||
                    it.entityCompany.shortName.contains(search, true))
        }.toSet()
    }
}