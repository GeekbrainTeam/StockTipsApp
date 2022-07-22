package com.amk.core.repository

import com.amk.core.entity.Company
import com.amk.core.entity.FavoriteCompanyShow
import kotlinx.coroutines.flow.Flow

interface RepositoryCompany {

    suspend fun createListOneDayYesterday(): Flow<List<Company>>

    suspend fun createListOneDayHalfYear(): Flow<List<Company>>

    suspend fun addFavoriteCompany(secId: String)

    suspend fun deleteFavoriteCompany(secId: String)

    suspend fun createFavoriteCompany(): Flow<List<FavoriteCompanyShow>>
}