package com.amk.core.entity

sealed class FaforiteFragmentState {
    object Loading : FaforiteFragmentState()
    class Failure(val msg: Throwable) : FaforiteFragmentState()
    class Success(val data: List<FavoriteCompanyShow>) : FaforiteFragmentState()
    object Empty : FaforiteFragmentState()
}
