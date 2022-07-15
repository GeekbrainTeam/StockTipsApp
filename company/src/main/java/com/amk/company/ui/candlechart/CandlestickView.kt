package com.amk.company.ui.candlechart

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.amk.company.ui.baseviewchart.*
import com.amk.company.ui.baseviewchart.ChartValue.COUNT_OF_VALUE_Y_AXIS
import com.amk.company.ui.baseviewchart.ChartValue.coordEndXAxis
import com.amk.company.ui.baseviewchart.ChartValue.coordZeroX
import com.amk.company.ui.baseviewchart.ChartValue.coordZeroY
import com.amk.company.ui.baseviewchart.ChartValue.currentX
import com.amk.company.ui.baseviewchart.ChartValue.heightPerValue
import com.amk.company.ui.baseviewchart.ChartValue.heightView
import com.amk.company.ui.baseviewchart.ChartValue.maxValueYAxis
import com.amk.company.ui.baseviewchart.ChartValue.minValueYAxis
import com.amk.company.ui.baseviewchart.ChartValue.stepXAxis
import com.amk.company.ui.baseviewchart.ChartValue.widthPerView
import com.amk.core.entity.Company

class CandlestickViewImpl @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseView(context, attrs, defStyleAttr) {

    private val candleList: MutableList<Company> = mutableListOf()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        maxValueYAxis = candleList.diffCandlestick()
        minValueYAxis = candleList.minCandleList()
        ChartValue.stepValueYAxis = maxValueYAxis / COUNT_OF_VALUE_Y_AXIS
        coordEndXAxis = widthPerView * candleList.size - coordZeroX
        scrollToLeft()

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

    @RequiresApi(Build.VERSION_CODES.O)
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

    private fun onDrawShadow(canvas: Canvas, candle: Company) {
        val positionX = (currentX + widthPerView / 2).toFloat()

        val top = coordZeroY - (heightPerValue * (candle.high - minValueYAxis)).toFloat()
        val bottom =
            coordZeroY - (heightPerValue * (candle.low - minValueYAxis)).toFloat()
        canvas.drawLine(positionX, top, positionX, bottom, candle.colorBody().paint)
    }

    private fun onDrawBody(canvas: Canvas, candle: Company) {
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

    fun drawCandles(candles: List<Company>) {
        candleList.clear()
        candleList.addAll(candles)
        requestLayout()
        invalidate()
    }

    private fun scrollToLeft() {
//        post { scrollBy(600, 0) }
    }

    fun showNoData() {
        Toast.makeText(this.context, "Error! No data!", Toast.LENGTH_SHORT).show()
    }
}