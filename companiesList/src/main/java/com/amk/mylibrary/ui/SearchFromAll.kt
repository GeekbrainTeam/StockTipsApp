package com.amk.mylibrary.ui

import androidx.lifecycle.LiveData

interface SearchFromAll {
    fun getQueryCompany() : LiveData<String>
}