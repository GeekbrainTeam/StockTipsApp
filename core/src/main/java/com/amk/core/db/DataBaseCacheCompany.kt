package com.amk.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amk.core.entity.CashCompanyAfterYesterday
import com.amk.core.entity.CashCompanyHalfYear
import com.amk.core.entity.CashCompanyOneDay
import com.amk.core.entity.FavoriteCompany
import com.amk.core.utils.DATABASE_NAME

@Database(
    version = 1,
    entities = [CashCompanyOneDay::class,
        CashCompanyAfterYesterday::class,
        CashCompanyHalfYear::class,
        FavoriteCompany::class],
    exportSchema = false
)
abstract class DataBaseCacheCompany : RoomDatabase() {

    abstract fun cacheDao(): CashDao

    companion object {

        @Volatile
        private var instanse: DataBaseCacheCompany? = null

        fun getDatabase(context: Context): DataBaseCacheCompany {
            return instanse ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseCacheCompany::class.java,
                    DATABASE_NAME
                ).build()
                instanse = instance
                instance
            }
        }
    }
}
