package ru.amk.favorite.ui.candlechart

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.Toast

import com.amk.core.entity.EntityCompany
import ru.amk.favorite.ui.baseviewchart.*
import ru.amk.favorite.ui.baseviewchart.ChartValue.COUNT_OF_VALUE_Y_AXIS
import ru.amk.favorite.ui.baseviewchart.ChartValue.coordEndXAxis
import ru.amk.favorite.ui.baseviewchart.ChartValue.coordZeroX
import ru.amk.favorite.ui.baseviewchart.ChartValue.coordZeroY
import ru.amk.favorite.ui.baseviewchart.ChartValue.currentX
import ru.amk.favorite.ui.baseviewchart.ChartValue.heightPerValue
import ru.amk.favorite.ui.baseviewchart.ChartValue.heightView
import ru.amk.favorite.ui.baseviewchart.ChartValue.maxValueYAxis
import ru.amk.favorite.ui.baseviewchart.ChartValue.minValueYAxis
import ru.amk.favorite.ui.baseviewchart.ChartValue.stepXAxis
import ru.amk.favorite.ui.baseviewchart.ChartValue.widthPerView

class CandlestickViewFavoriteImpl @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseViewFavorite(context, attrs, defStyleAttr) {

    private val candleList: MutableList<EntityCompany> = mutableListOf()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        maxValueYAxis = candleList.diffCandlestick()
        minValueYAxis = candleList.minCandleList()
        ChartValue.stepValueYAxis = maxValueYAxis / COUNT_OF_VALUE_Y_AXIS
        coordEndXAxis = widthPerView * candleList.size - coordZeroX

        when (widthMode) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY, MeasureSpec.UNSPECIFIED -> {
                stepXAxis = widthPerView.toFloat()
                if (candleList.isNotEmpty()) {
                    heightPerValue = coordZeroY / maxValueYAxis
                }
                setMeasuredDimension(widthPerView * candleList.size + 1, heightView)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {

        if (candleList.isNotEmpty()) {
            currentX = 0
            heightPerValue = coordZeroY / candleList.diffCandlestick()

            canvas?.let { onDrawCoordinateGrid(canvas) }
            for ((index, item) in candleList.withIndex()) {
                canvas?.let {
                    onDrawXAxisSignatures(canvas, item.tradeDate, index)
                    onDrawShadow(canvas, item)
                    onDrawBody(canvas, item)
                }
                currentX += widthPerView
            }
        }
    }

    fun getWidthView(): Int = widthPerView * candleList.size

    private fun onDrawShadow(canvas: Canvas, candle: EntityCompany) {
        val positionX = (currentX + widthPerView / 2).toFloat()

        val top = coordZeroY - (heightPerValue * (candle.high - minValueYAxis)).toFloat()
        val bottom =
            coordZeroY - (heightPerValue * (candle.low - minValueYAxis)).toFloat()
        canvas.drawLine(positionX, top, positionX, bottom, candle.colorBody().paint)
    }

    private fun onDrawBody(canvas: Canvas, candle: EntityCompany) {
        val left = (currentX + widthPerView / 2 - widthPerView / 4).toFloat()
        val top = coordZeroY - (heightPerValue * (candle.open - minValueYAxis)).toFloat()
        val right = (currentX + widthPerView / 2 + widthPerView / 4).toFloat()
        val bottom =
            coordZeroY - (heightPerValue * (candle.close - minValueYAxis)).toFloat()
        if (candle.open == candle.close) {
            canvas.drawLine(left, top, right, bottom, candle.colorBody().paint)
        } else {
            canvas.drawRect(left, top, right, bottom, candle.colorBody().paint)
        }
    }

    fun drawCandles(candles: List<EntityCompany>) {
        candleList.clear()
        candleList.addAll(candles)
        requestLayout()
        invalidate()
    }


    fun showNoData() {
        Toast.makeText(this.context, "Error! No data!", Toast.LENGTH_SHORT).show()
    }
}