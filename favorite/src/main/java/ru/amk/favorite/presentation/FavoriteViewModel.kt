package ru.amk.favorite.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amk.core.repository.CacheRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import ru.amk.favorite.interactors.FaforiteFragmentState

class FavoriteViewModel(/*private val cacheRepository: CacheRepository*/) : ViewModel(), KoinComponent {

    private val cacheRepository =  CacheRepository()
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
                _companiesData.value =
                    FaforiteFragmentState.Success(cacheRepository.getFavoriteCompanies())
            } catch (error: Exception) {
                _companiesData.value = FaforiteFragmentState.Failure(error)
            }
        }
    }

    fun deleteFavoriteCompany (secId : String) {
        viewModelScope.launch {
            cacheRepository.deleteFavoriteCompany(secId)
        }
    }
}