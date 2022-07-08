package com.amk.core.repository

import androidx.lifecycle.LiveData
import com.amk.core.entity.Company

interface RepositoryInternet {
    fun getCompanies(): LiveData<List<Company>>
}