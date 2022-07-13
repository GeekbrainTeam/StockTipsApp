package com.amk.core.db

import androidx.room.*
import com.amk.core.entity.CacheCompanyAfterYesterday
import com.amk.core.entity.CacheCompanyHalfYear
import com.amk.core.entity.CacheCompanyOneDay
import com.amk.core.entity.FavoriteCompany
import com.amk.core.utils.*

@Dao
interface ChacheDao {

    @Insert(entity = CacheCompanyOneDay::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompanyOneDay(company: CacheCompanyOneDay)

    //@Delete(entity = CacheCompanyOneDay::class)
    @Query("DELETE FROM $COMPANY_OF_THE_DAY")
    suspend fun deleteListCompanyOneDay()

    @Query("SELECT * FROM $COMPANY_OF_THE_DAY")
    suspend fun getCompanyOneDay(): List<CacheCompanyOneDay>

    @Insert(entity = CacheCompanyAfterYesterday::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompanyAfterYesterday(company: CacheCompanyAfterYesterday)

    //@Delete(entity = CacheCompanyAfterYesterday::class)
    @Query("DELETE FROM $COMPANY_OF_AFTER_YESTERDAY")
    suspend fun deleteListCompanyAfterYesterday()

    @Query("SELECT * FROM $COMPANY_OF_AFTER_YESTERDAY")
    suspend fun getCompanyAfterYesterday(): List<CacheCompanyAfterYesterday>

    @Insert(entity = CacheCompanyHalfYear::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompanyHalfYear(company: CacheCompanyHalfYear)

    //@Delete(entity = CacheCompanyHalfYear::class)
    @Query("DELETE FROM $COMPANY_OF_HALF_YEAR")
    suspend fun deleteListCompanyHalfYear()

    @Query("SELECT * FROM $COMPANY_OF_HALF_YEAR")
    suspend fun getCompanyHalfYear(): List<CacheCompanyHalfYear>

    @Insert(entity = FavoriteCompany::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompany(company: FavoriteCompany)

    @Query("DELETE FROM $COMPANY_IS_FAVORITE WHERE $SEC_ID=:secId")
    suspend fun deleteFavoriteCompany(secId: String)

    @Query("SELECT * FROM $COMPANY_IS_FAVORITE")
    suspend fun getCompanyFavoriteCompany(): List<FavoriteCompany>
}