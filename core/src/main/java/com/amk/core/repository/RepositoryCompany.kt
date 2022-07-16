package com.amk.core.repository

import com.amk.core.entity.Company

interface RepositoryCompany {

    suspend fun сreateListOneDayYesterday(): List<Company>

    suspend fun сreateListOneDayHalfYear(): List<Company>

    suspend fun addFavoriteCompany(secId: String)

    suspend fun deleteFavoriteCompany(secId: String)
}