package com.amk.core.entity.alternative

import com.google.gson.annotations.SerializedName

data class ResponseStructure(
    val history: History,

    @field:SerializedName("history.cursor")
    val historyCursor: HistoryCursor
)

data class History(
    val metadata: HistoryMetadata,
    val columns: List<String>,
    val data: List<Company> // список компаний
)

data class Company(
    val companyInfoList: List<String> // info список из 21го элемента описанного в метадате
)

data class HistoryMetadata(
    @field:SerializedName("BOARDID")
    val boardId: TypeBytesMaxSize,

    @field:SerializedName("TRADEDATE")
    val tradeDate: TypeBytesMaxSize,

    @field:SerializedName("SHORTNAME")
    val shortname: TypeBytesMaxSize,

    @field:SerializedName("SECID")
    val secId: TypeBytesMaxSize,

    @field:SerializedName("NUMTRADES")
    val numTrades: Type,

    @field:SerializedName("VALUE")
    val value: Type,

    @field:SerializedName("OPEN")
    val open: Type,

    @field:SerializedName("LOW")
    val low: Type,

    @field:SerializedName("HIGH")
    val high: Type,

    @field:SerializedName("LEGALCLOSEPRICE")
    val legalcloseprice: Type,

    @field:SerializedName("WAPRICE")
    val waprice: Type,

    @field:SerializedName("CLOSE")
    val close: Type,

    @field:SerializedName("VOLUME")
    val volume: Type,

    @field:SerializedName("MARKETPRICE2")
    val marketPrice2: Type,

    @field:SerializedName("MARKETPRICE3")
    val marketPrice3: Type,

    @field:SerializedName("ADMITTEDQUOTE")
    val admittedQuote: Type,

    @field:SerializedName("MP2VALTRD")
    val mp2ValTrd: Type,

    @field:SerializedName("MARKETPRICE3TRADESVALUE")
    val marketPrice3TradesValue: Type,

    @field:SerializedName("ADMITTEDVALUE")
    val admittedValue: Type,

    @field:SerializedName("WAVAL")
    val waVal: Type,

    @field:SerializedName("TRADINGSESSION")
    val tradingSession: Type
)

data class Type(
    val type: String
)

data class TypeBytesMaxSize(
    val type: String,
    val bytes: Long,

    @field:SerializedName("max_size")
    val maxSize: Long
)

data class HistoryCursor(
    val metadata: HistoryCursorMetadata,
    val columns: List<String>,
    val data: List<List<Long>>
)

data class HistoryCursorMetadata(
    @field:SerializedName("INDEX")
    val index: Type,

    @field:SerializedName("TOTAL")
    val total: Type,

    @field:SerializedName("PAGESIZE")
    val pageSize: Type
)
