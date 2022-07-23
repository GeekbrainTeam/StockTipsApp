package ru.amk.favorite.interactors

import com.amk.core.entity.EntityFavoriteCompany

sealed class FaforiteFragmentState {
    object Loading : FaforiteFragmentState()
    class Failure(val msg: Throwable) : FaforiteFragmentState()
    class Success(val data: List<EntityFavoriteCompany>) : FaforiteFragmentState()
    object Empty : FaforiteFragmentState()
}
