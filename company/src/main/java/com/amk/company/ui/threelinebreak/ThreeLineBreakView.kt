package com.amk.company.ui.threelinebreak

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.amk.company.ui.baseviewchart.BaseView
import com.amk.company.ui.baseviewchart.ChartValue
import com.amk.company.ui.baseviewchart.ChartValue.coordZeroY
import com.amk.company.ui.baseviewchart.ChartValue.currentX
import com.amk.company.ui.baseviewchart.ChartValue.heightPerValue
import com.amk.company.ui.baseviewchart.ChartValue.minValueYAxis
import com.amk.company.ui.baseviewchart.ChartValue.widthPerView
import com.amk.core.entity.EntityCompany
import com.amk.core.entity.ThreeLineBreakCompany
import com.amk.core.interactors.ThreeLineBreakCompanyFactory

class ThreeLineBreakView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseView(context, attrs, defStyleAttr) {

    private val threeLineBreaks: MutableList<ThreeLineBreakCompany> = mutableListOf()
    fun drawThreeLine(companyList: List<EntityCompany>) {
        threeLineBreaks.clear()
        threeLineBreaks.addAll(ThreeLineBreakCompanyFactory(companyList).getThreeLineCompanies())
        requestLayout()
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        when (widthMode) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY, MeasureSpec.UNSPECIFIED -> {
                ChartValue.stepXAxis = widthPerView.toFloat()
                if (threeLineBreaks.isNotEmpty()) {
                    heightPerValue = coordZeroY / ChartValue.maxValueYAxis
                }
                setMeasuredDimension(widthPerView * threeLineBreaks.size, ChartValue.heightView)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if (threeLineBreaks.isNotEmpty()) {
            currentX = 0

            canvas?.let { onDrawCoordinateGrid(canvas) }
            for ((index, item) in threeLineBreaks.withIndex()) {
                canvas?.let {
                    onDrawXAxisSignatures(canvas, item.date, index)
                    onDrawBody(canvas, item)
                }
                currentX += widthPerView
            }
        }
    }

    private fun onDrawBody(canvas: Canvas, threeLineBreak: ThreeLineBreakCompany) {
        val left = (currentX + widthPerView / 2 - widthPerView / 2 + 1).toFloat()
        val top =
            coordZeroY - (heightPerValue * (threeLineBreak.maxPrice - minValueYAxis)).toFloat()
        val right = (currentX + widthPerView / 2 + widthPerView / 2 - 1).toFloat()
        val bottom =
            coordZeroY - (heightPerValue * (threeLineBreak.minPrice - minValueYAxis)).toFloat()
        if (threeLineBreak.maxPrice == threeLineBreak.minPrice) {
            canvas.drawLine(left, top, right, bottom, threeLineBreak.color.paint)
        } else {
            canvas.drawRect(left, top, right, bottom, threeLineBreak.color.paint)
        }
    }
}