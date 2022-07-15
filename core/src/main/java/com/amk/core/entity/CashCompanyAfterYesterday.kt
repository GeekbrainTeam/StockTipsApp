package com.amk.core.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amk.core.utils.*
import java.io.Serializable

@Entity(tableName = COMPANY_OF_AFTER_YESTERDAY)
data class CashCompanyAfterYesterday(
    @PrimaryKey
    @ColumnInfo(name = SEC_ID)
    override val secId: String,
    @ColumnInfo(name = TRADE_DATA)
    override val tradeDate: String,
    @ColumnInfo(name = SHORT_NAME)
    override val shortName: String,
    @ColumnInfo(name = OPEN)
    override val open: Double,
    @ColumnInfo(name = LOW)
    override val low: Double,
    @ColumnInfo(name = HIGH)
    override val high: Double,
    @ColumnInfo(name = CLOSE)
    override val close: Double
) : BaseCashCompany, Serializable
