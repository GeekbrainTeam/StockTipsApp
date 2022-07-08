package com.amk.core.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amk.core.utils.*
import java.util.*

@Entity(tableName = NOTE_TABLE)
data class Company(
    @ColumnInfo(name = CONTAINS_NULL)
    val containsNulls: Boolean,
    @ColumnInfo(name = TRADE_DATA)
    val tradeDate: Date,
    @PrimaryKey
    @ColumnInfo(name = SHORT_NAME)
    val shortName: String,
    @ColumnInfo(name = SEC_ID)
    val secId: String,
    @ColumnInfo(name = OPEN)
    val open: Double = 0.0,
    @ColumnInfo(name = LOW)
    val low: Double = 0.0,
    @ColumnInfo(name = HIGH)
    val high: Double = 0.0,
    @ColumnInfo(name = CLOSE)
    val close: Double = 0.0
)
