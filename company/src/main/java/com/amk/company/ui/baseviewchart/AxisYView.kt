package com.amk.company.ui.baseviewchart

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.amk.company.ui.baseviewchart.ChartValue.COUNT_OF_VALUE_Y_AXIS
import com.amk.company.ui.baseviewchart.ChartValue.SEGMENT_LENGTH
import com.amk.company.ui.baseviewchart.ChartValue.coordEndYAxis
import com.amk.company.ui.baseviewchart.ChartValue.coordZeroX
import com.amk.company.ui.baseviewchart.ChartValue.coordZeroY
import com.amk.company.ui.baseviewchart.ChartValue.heightView
import com.amk.company.ui.baseviewchart.ChartValue.maxValueYAxis
import com.amk.company.ui.baseviewchart.ChartValue.stepValueYAxis
import com.amk.company.ui.baseviewchart.ChartValue.stepYAxis
import com.amk.core.entity.Company

interface AxisYView {
    fun drawAxisY(candles: List<Company>)
}

class AxisYViewImpl @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AxisYView, BaseView(context, attrs, defStyleAttr) {

    private var _defaultWidth = context.resources.displayMetrics.density * 50
    private var _signatureCoordX = 1f
    private var _signatureCoordY = 1f

    private var _minValue: Double = 0.0
    private var _stepNextValue: Double = 0.0

    private val candleList: MutableList<Company> = mutableListOf()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)
        maxValueYAxis = candleList.diffCandlestick()
        ChartValue.minValueYAxis = candleList.minCandleList()
        stepValueYAxis = maxValueYAxis / COUNT_OF_VALUE_Y_AXIS

        coordZeroX = 2f
        setMeasuredDimension(_defaultWidth.toInt(), heightView)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            canvas.drawLine(
                coordZeroX,
                coordZeroY,
                coordZeroX,
                coordEndYAxis,
                Paints.paintAxis
            )//ось Y
            for (item in 1..COUNT_OF_VALUE_Y_AXIS) {
                val startY = item * stepYAxis
                val startX = coordZeroX - SEGMENT_LENGTH
                val stopX = coordZeroX + SEGMENT_LENGTH
                canvas.drawLine(startX, startY, stopX, startY, Paints.paintAxis)
                canvas.drawText(
                    "${(maxValueYAxis - (stepValueYAxis * item) + candleList.min()).roundForAxisSignature()}",
                    _signatureCoordX + 15f,
                    startY,
                    Paints.paintText
                )
            }
        }
    }

    override fun drawAxisY(candles: List<Company>) {
        candleList.clear()
        candleList.addAll(candles)
        requestLayout()
        invalidate()
    }
}