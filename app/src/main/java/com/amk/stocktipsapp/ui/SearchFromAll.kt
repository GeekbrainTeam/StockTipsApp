package com.amk.stocktipsapp.UI

import androidx.lifecycle.LiveData

interface SearchFromAll {
    fun getQueryCompany() : LiveData<String>
}