package com.amk.core.repository

import com.amk.core.entity.Company

interface Repository {
    fun setView(view: View)
    fun getCompanies()
    fun getCompaniesLastData() : List<Company>
    fun getCompanyCandles(secId: String)

}