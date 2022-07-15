package com.amk.core.repository

import com.amk.core.entity.Company

interface RepositoryCompany {

    suspend fun createListOneDayYesterday(): List<Company>

    suspend fun createListOneDayHalfYear(): List<Company>
}