package com.amk.company.ui.linechart

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.amk.company.ui.baseviewchart.BaseView
import com.amk.company.ui.baseviewchart.ChartValue
import com.amk.company.ui.baseviewchart.diffCandlestick
import com.amk.company.ui.baseviewchart.minCandleList
import com.amk.core.entity.ColorCandle
import com.amk.core.entity.EntityCompany

class LineChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseView(context, attrs, defStyleAttr) {

    private val companiesByDate: MutableList<EntityCompany> = mutableListOf()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        ChartValue.maxValueYAxis = companiesByDate.diffCandlestick()
        ChartValue.minValueYAxis = companiesByDate.minCandleList()
        ChartValue.stepValueYAxis = ChartValue.maxValueYAxis / ChartValue.COUNT_OF_VALUE_Y_AXIS
        ChartValue.coordEndXAxis =
            ChartValue.widthPerView * companiesByDate.size - ChartValue.coordZeroX

        when (widthMode) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY, MeasureSpec.UNSPECIFIED -> {
                ChartValue.stepXAxis = ChartValue.widthPerView.toFloat()
                if (companiesByDate.isNotEmpty()) {
                    ChartValue.heightPerValue = ChartValue.coordZeroY / ChartValue.maxValueYAxis
                }
                setMeasuredDimension(
                    ChartValue.widthPerView * companiesByDate.size + 1,
                    ChartValue.heightView
                )
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {

        if (companiesByDate.isNotEmpty()) {
            ChartValue.currentX = 0
            ChartValue.heightPerValue = ChartValue.coordZeroY / companiesByDate.diffCandlestick()
            canvas?.let { onDrawCoordinateGrid(canvas) }
            var previousCompanyByDate = companiesByDate.first()
            val color: ColorCandle =
                if (companiesByDate.last().open < companiesByDate.last().close) ColorCandle.PriceUp() else ColorCandle.PriceDown()
            if (companiesByDate.size < 270) {
                for ((index, item) in companiesByDate.withIndex()) {
                    if (index == 0) continue
                    canvas?.let {
                        onDrawXAxisSignaturesLessYear(canvas, item.tradeDate, index)
                        onDrawLine(canvas, item, previousCompanyByDate, color)
                        previousCompanyByDate = item
                    }
                    ChartValue.currentX += ChartValue.widthPerView
                }
            } else {
                for ((index, item) in companiesByDate.withIndex()) {
                    if (index == 0) continue
                    canvas?.let {
                        onDrawXAxisSignaturesMoreYear(canvas, item.tradeDate, index)
                        onDrawLine(canvas, item, previousCompanyByDate, color)
                        previousCompanyByDate = item
                    }
                    ChartValue.currentX += ChartValue.widthPerView
                }
            }

        }
    }

    private fun onDrawLine(
        canvas: Canvas,
        itemCompany: EntityCompany,
        previousItemCompany: EntityCompany,
        colorCandle: ColorCandle
    ) {
        val startX = (ChartValue.currentX).toFloat()
        val startY =
            ChartValue.coordZeroY - (ChartValue.heightPerValue * (previousItemCompany.close - ChartValue.minValueYAxis)).toFloat()
        val endX = (ChartValue.currentX + ChartValue.widthPerView).toFloat()
        val endY =
            ChartValue.coordZeroY - (ChartValue.heightPerValue * (itemCompany.close - ChartValue.minValueYAxis)).toFloat()

        canvas.drawLine(startX, startY, endX, endY, colorCandle.paint)
    }

    fun drawLine(candles: List<EntityCompany>) {
        companiesByDate.clear()
        companiesByDate.addAll(candles)
        requestLayout()
        invalidate()
    }
}