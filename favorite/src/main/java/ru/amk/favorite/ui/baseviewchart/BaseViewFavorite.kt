package ru.amk.favorite.ui.baseviewchart

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.amk.core.entity.EntityCompany
import com.amk.core.utils.convertForXAxisLessYear
import ru.amk.favorite.ui.baseviewchart.ChartValue.COUNT_OF_VALUE_Y_AXIS
import ru.amk.favorite.ui.baseviewchart.ChartValue.OFFSET_AXIS_Y
import ru.amk.favorite.ui.baseviewchart.ChartValue.SEGMENT_LENGTH
import java.util.*

abstract class BaseViewFavorite @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    internal var divScreen: Double = 3.5

    protected var coordZeroX = 0f// начало оси X
    protected var coordEndXAxis = 0f//Конец оси X
    protected var stepYAxis = 0f//Расстояние между сигнатурами оси Y
    protected var maxValueYAxis = 0.0//максимально значение по оси Y
    protected var heightView = 0
    protected val widthPerView: Int = 50
    protected var coordZeroY = 1f// начало оси Y
    protected var coordEndYAxis = 0f//Конец оси Yinternal
    protected var heightPerValue: Double = 0.0
    protected var currentX: Int = 0
    protected var widthSize = 0
    protected var stepXAxis = 0f//Расстояние между подписями оси X
    protected var minValueYAxis: Double = 0.0
    protected var stepValueYAxis =
        maxValueYAxis / COUNT_OF_VALUE_Y_AXIS//шаг значение по оси

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        heightView = (heightSize / divScreen).toInt()
        coordZeroY = heightView - OFFSET_AXIS_Y

        stepYAxis = coordZeroY / COUNT_OF_VALUE_Y_AXIS.toFloat()
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        this.widthSize = MeasureSpec.getSize(widthMeasureSpec)
    }

    fun onDrawXAxisSignatures(canvas: Canvas, date: Date, index: Int) {
        val positionX = (currentX + widthPerView / 2).toFloat()
        //Рисование сигнатуры на оси Х
        val startY = coordZeroY - SEGMENT_LENGTH
        val stopY = coordZeroY + SEGMENT_LENGTH
        canvas.drawLine(positionX, startY, positionX, stopY, Paints.paintAxis)
        canvas.drawLine(positionX, stopY, positionX, coordEndYAxis, Paints.paintAxisDottedLine)
        if (index % 2 == 0) {
            canvas.drawText(date.convertForXAxisLessYear(), positionX - 40f, stopY + 30f, Paints.paintText)
        }
    }

    fun onDrawCoordinateGrid(canvas: Canvas) {
        canvas.drawLine(coordZeroX, coordZeroY, coordEndXAxis, coordZeroY, Paints.paintAxis)//ось X
        canvas.drawLine(coordZeroX, 0f, coordEndXAxis, 0f, Paints.paintAxis)//ось X

        for (item in 1..COUNT_OF_VALUE_Y_AXIS) {
            val startY = item * stepYAxis
            val stopX = coordZeroX
            //горизонтальный пунктир
            canvas.drawLine(stopX, startY, coordEndXAxis, startY, Paints.paintAxisDottedLine)
        }
    }
}

fun List<EntityCompany>.diffCandlestick(): Double {
    if (this.isEmpty()) return -1.0
    val max = this.map { it.high }.maxOf { it }
    val min = this.map { it.low }.minOf { it }
    return diff(max, min)
}

fun diff(max: Double, min: Double): Double = (max + max / 50) - (min - min / 50)

fun Double.roundForAxisSignature(): Double {
    return when (this) {
        in 0.0..1.0 -> {
            String.format(Locale.US, "%.4f", this).toDouble()
        }
        in 1.0..10.0 -> {
            String.format(Locale.US, "%.2f", this).toDouble()
        }
        in 10.0..100.0 -> {
            String.format(Locale.US, "%.1f", this).toDouble()
        }
        else -> {
            String.format(Locale.US, "%.0f", this).toDouble()
        }
    }
}

internal fun List<EntityCompany>.min(): Double {
    return if (this.isNotEmpty()) {
        val min = this.map { it.low }.minOf { it }
        (min - min / 50)
    } else {
        0.0
    }
}