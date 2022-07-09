package com.amk.core.repository

interface Repository {
    fun setView(view: View)
    fun getCompanies()
    fun getCompanyCandles(secId: String)

}