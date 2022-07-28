package ru.amk.favorite.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amk.core.entity.FaforiteFragmentState
import com.amk.core.repository.RepositoryCompany
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FavoriteViewModel : ViewModel(), KoinComponent {

    private val cacheRepository by inject<RepositoryCompany>()
    private val _companiesData =
        MutableLiveData<FaforiteFragmentState>(FaforiteFragmentState.Empty)
    val companiesData = _companiesData

    init {
        _companiesData.value = FaforiteFragmentState.Loading
        getFavoriteCompanyIsShow()
    }
    private fun getFavoriteCompanyIsShow() {
        viewModelScope.launch {
            try {
                cacheRepository.createFavoriteCompany().collect {

                    _companiesData.value =
                        FaforiteFragmentState.Success(it)
                }
            } catch (error: Exception) {
                _companiesData.value = FaforiteFragmentState.Failure(error)
            }
        }
    }

    fun filteredFavoriteCompany(search: String?) {
        if (search.isNullOrBlank()) {
            viewModelScope.launch {
                cacheRepository.createFavoriteCompany().collect {
                    _companiesData.value =
                        FaforiteFragmentState.Success(it)
                }
            }
        } else {
            viewModelScope.launch {
                cacheRepository.createFavoriteCompany().collect {
                    _companiesData.value =
                        FaforiteFragmentState.Success(it.filter {
                            (it.secId.contains(search, true) ||
                                    it.name.contains(search, true))
                        })
                }
            }
        }
    }

    fun deleteFavoriteCompany(secId: String) {
        viewModelScope.launch {
            cacheRepository.deleteFavoriteCompany(secId)
            getFavoriteCompanyIsShow()
        }
    }
}