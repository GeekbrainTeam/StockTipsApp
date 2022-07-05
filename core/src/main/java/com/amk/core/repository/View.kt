package com.amk.core.repository

import android.view.View
import com.amk.core.entity.Company

interface View {
    fun showResult(result: MutableList<Company>)
    fun showError(error: String)
}