package com.amk.core.repository

import com.amk.core.entity.Company

interface RepositoryCompany {
    suspend fun CreateListOneDayYesterday(): List<Company>
    suspend fun CreateListOneDayHalfYear(): List<Company>
    suspend fun addFavoriteCompany(secId: String)
    suspend fun deleteFavoriteCompany(secId: String)
}