package ru.amk.favorite.ui.baseviewchart

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.amk.core.entity.EntityCompany
import ru.amk.favorite.ui.baseviewchart.ChartValue.COUNT_OF_VALUE_Y_AXIS
import ru.amk.favorite.ui.baseviewchart.ChartValue.SEGMENT_LENGTH
import ru.amk.favorite.ui.baseviewchart.ChartValue.coordEndYAxis
import ru.amk.favorite.ui.baseviewchart.ChartValue.coordZeroX
import ru.amk.favorite.ui.baseviewchart.ChartValue.coordZeroY
import ru.amk.favorite.ui.baseviewchart.ChartValue.heightView
import ru.amk.favorite.ui.baseviewchart.ChartValue.maxValueYAxis
import ru.amk.favorite.ui.baseviewchart.ChartValue.stepValueYAxis
import ru.amk.favorite.ui.baseviewchart.ChartValue.stepYAxis

interface AxisYViewFavorite {
    fun drawAxisY(candles: List<EntityCompany>)
}

class AxisYViewImpl @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AxisYViewFavorite, BaseViewFavorite (context, attrs, defStyleAttr) {

    private var _defaultWidth = context.resources.displayMetrics.density * 50
    private var _signatureCoordX = 1f
    private var _signatureCoordY = 1f

    private var _minValue: Double = 0.0
    private var _stepNextValue: Double = 0.0

    private val candleList: MutableList<EntityCompany> = mutableListOf()

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

    override fun drawAxisY(candles: List<EntityCompany>) {
        candleList.clear()
        candleList.addAll(candles)
        requestLayout()
        invalidate()
    }
}