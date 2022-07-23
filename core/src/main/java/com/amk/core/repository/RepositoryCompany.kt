package com.amk.core.repository

import com.amk.core.entity.Company
import com.amk.core.entity.EntityFavoriteCompany
import com.amk.core.entity.FavoriteCompany
import kotlinx.coroutines.flow.Flow

interface RepositoryCompany {

    suspend fun createListOneDayYesterday(): Flow<Set<Company>>

    suspend fun createListOneDayHalfYear(): Flow<Set<Company>>

    suspend fun addFavoriteCompany(secId: String)

    suspend fun deleteFavoriteCompany(secId: String)

    suspend fun getAllFavorite(): List<EntityFavoriteCompany>

    suspend fun createFavoriteCompany(): Flow<List<FavoriteCompany>>
}