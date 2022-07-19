package com.amk.core.repository

import com.amk.core.entity.Company
import com.amk.core.entity.FavoriteCompanyShow

interface RepositoryCompany {

    suspend fun createListOneDayYesterday(): List<Company>

    suspend fun createListOneDayHalfYear(): List<Company>

    suspend fun addFavoriteCompany(secId: String)

    suspend fun deleteFavoriteCompany(secId: String)

    suspend fun createFavoriteCompany(): List<FavoriteCompanyShow>
}