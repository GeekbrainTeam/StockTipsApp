package ru.amk.favorite.ui

import androidx.lifecycle.LiveData

interface SearchFromFavorite {
    fun getQueryFavoriteCompany() : LiveData<String>
}