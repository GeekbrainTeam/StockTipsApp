package com.amk.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amk.core.entity.CashCompanyAfterYesterday
import com.amk.core.entity.CashCompanyHalfYear
import com.amk.core.entity.CashCompanyOneDay
import com.amk.core.entity.FavoriteCompany
import com.amk.core.utils.*

@Dao
interface CashDao {

    @Insert(entity = CashCompanyOneDay::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompanyOneDay(company: CashCompanyOneDay)

    @Query("DELETE FROM $COMPANY_OF_THE_DAY")
    suspend fun deleteListCompanyOneDay()

    @Query("SELECT * FROM $COMPANY_OF_THE_DAY")
    suspend fun getCompanyOneDay(): List<CashCompanyOneDay>

    @Insert(entity = CashCompanyAfterYesterday::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompanyAfterYesterday(company: CashCompanyAfterYesterday)

    @Query("DELETE FROM $COMPANY_OF_AFTER_YESTERDAY")
    suspend fun deleteListCompanyAfterYesterday()

    @Query("SELECT * FROM $COMPANY_OF_AFTER_YESTERDAY")
    suspend fun getCompanyAfterYesterday(): List<CashCompanyAfterYesterday>

    @Insert(entity = CashCompanyHalfYear::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompanyHalfYear(company: CashCompanyHalfYear)

    @Query("DELETE FROM $COMPANY_OF_HALF_YEAR")
    suspend fun deleteListCompanyHalfYear()

    @Query("SELECT * FROM $COMPANY_OF_HALF_YEAR")
    suspend fun getCompanyHalfYear(): List<CashCompanyHalfYear>

    @Insert(entity = FavoriteCompany::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompany(company: FavoriteCompany)

    @Query("DELETE FROM $COMPANY_IS_FAVORITE WHERE $SEC_ID=:secId")
    suspend fun deleteFavoriteCompany(secId: String)

    @Query("SELECT * FROM $COMPANY_IS_FAVORITE")
    suspend fun getCompanyFavoriteCompany(): List<FavoriteCompany>
}