package com.amk.core.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amk.core.utils.*
import java.io.Serializable

@Entity(tableName = COMPANY_OF_AFTER_YESTERDAY)
data class CacheCompanyAfterYesterday(
    @PrimaryKey
    @ColumnInfo (name = SEC_ID)
    val secId: String,
    @ColumnInfo (name = TRADE_DATA)
    val tradeData: String,
    @ColumnInfo (name = SHORT_NAME)
    val shortName: String,
    @ColumnInfo (name = OPEN)
    val open: Double,
    @ColumnInfo (name = LOW)
    val low: Double,
    @ColumnInfo (name = HIGH)
    val high: Double,
    @ColumnInfo (name = CLOSE)
    val close: Double
) : Serializable
