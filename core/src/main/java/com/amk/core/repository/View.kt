package com.amk.core.repository

interface View {
    fun showResult(result: String)
    fun showError(error: String)
}