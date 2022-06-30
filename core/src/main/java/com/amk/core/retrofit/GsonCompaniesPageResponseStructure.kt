package com.amk.core.retrofit

import com.google.gson.annotations.SerializedName

data class GsonCompaniesPageResponseStructure(
    val history: History,

    @field:SerializedName("history.cursor")
    val historyCursor: HistoryCursor
)


data class History(
    val metadata: HistoryMetadata,
    val columns: List<String>,
    val data: List<List<String?>> //data список компаний, вложенный список(одна компания) из 21го элемента для одной компании, описанные в метадате
)

data class HistoryMetadata(
    @field:SerializedName("BOARDID")
    val boardId: JsonTypeBytesMaxSizeWrapper,

    @field:SerializedName("TRADEDATE")
    val tradeDate: JsonTypeBytesMaxSizeWrapper,

    @field:SerializedName("SHORTNAME")
    val shortName: JsonTypeBytesMaxSizeWrapper,

    @field:SerializedName("SECID")
    val secId: JsonTypeBytesMaxSizeWrapper,

    @field:SerializedName("NUMTRADES")
    val numTrades: JsonTypeWrapper,

    @field:SerializedName("VALUE")
    val value: JsonTypeWrapper,

    @field:SerializedName("OPEN")
    val open: JsonTypeWrapper,

    @field:SerializedName("LOW")
    val low: JsonTypeWrapper,

    @field:SerializedName("HIGH")
    val high: JsonTypeWrapper,

    @field:SerializedName("LEGALCLOSEPRICE")
    val legalClosePrice: JsonTypeWrapper,

    @field:SerializedName("WAPRICE")
    val waPrice: JsonTypeWrapper,

    @field:SerializedName("CLOSE")
    val close: JsonTypeWrapper,

    @field:SerializedName("VOLUME")
    val volume: JsonTypeWrapper,

    @field:SerializedName("MARKETPRICE2")
    val marketPrice2: JsonTypeWrapper,

    @field:SerializedName("MARKETPRICE3")
    val marketPrice3: JsonTypeWrapper,

    @field:SerializedName("ADMITTEDQUOTE")
    val admittedQuote: JsonTypeWrapper,

    @field:SerializedName("MP2VALTRD")
    val mp2ValTrd: JsonTypeWrapper,

    @field:SerializedName("MARKETPRICE3TRADESVALUE")
    val marketPrice3TradesValue: JsonTypeWrapper,

    @field:SerializedName("ADMITTEDVALUE")
    val admittedValue: JsonTypeWrapper,

    @field:SerializedName("WAVAL")
    val waVal: JsonTypeWrapper,

    @field:SerializedName("TRADINGSESSION")
    val tradingSession: JsonTypeWrapper
)

data class JsonTypeWrapper(
    val type: String
)

data class JsonTypeBytesMaxSizeWrapper(
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
    val index: JsonTypeWrapper,

    @field:SerializedName("TOTAL")
    val total: JsonTypeWrapper,

    @field:SerializedName("PAGESIZE")
    val pageSize: JsonTypeWrapper
)