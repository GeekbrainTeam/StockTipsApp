package ru.amk.favorite.interactors

import com.amk.core.entity.FavoriteCompany

sealed class FaforiteFragmentState {
    object Loading : FaforiteFragmentState()
    class Failure(val msg:Throwable) : FaforiteFragmentState()
    class Success(val data:List<FavoriteCompany>) : FaforiteFragmentState()
    object Empty : FaforiteFragmentState()
}
