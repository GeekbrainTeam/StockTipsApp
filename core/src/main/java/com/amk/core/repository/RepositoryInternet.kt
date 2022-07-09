package com.amk.core.repository

import androidx.lifecycle.LiveData
import com.amk.core.entity.Company

interface RepositoryInternet {
    fun getCompanies(): List<Company>
    fun getCompaniesLastData() : List<Company>
}