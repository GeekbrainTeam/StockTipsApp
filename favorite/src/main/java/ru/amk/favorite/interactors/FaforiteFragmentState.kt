package ru.amk.favorite.interactors

import com.amk.core.entity.FavoriteCompanyShow

sealed class FaforiteFragmentState {
    object Loading : FaforiteFragmentState()
    class Failure(val msg:Throwable) : FaforiteFragmentState()
    class Success(val data:List<FavoriteCompanyShow>) : FaforiteFragmentState()
    object Empty : FaforiteFragmentState()
}
