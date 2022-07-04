package com.amk.core.repository

import com.amk.core.entity.Company

interface View {
    fun setRecyclerView(result: MutableList<Company>)
    fun showError(error: String)
}