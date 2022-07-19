package ru.amk.favorite.ui

import android.os.Bundle
import android.view.View
import com.amk.core.ui.BaseFragment
import ru.amk.favorite.R
import ru.amk.favorite.databinding.FragmentFavoriteBinding
import ru.amk.favorite.interactors.StatesFavoriteInteractor
import ru.amk.favorite.presentation.FavoriteViewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    override fun getViewBinding() = FragmentFavoriteBinding.inflate(layoutInflater)
    override fun getVModelClass() = FavoriteViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    val layoutInflater =layoutInflater
    viewModel.companiesData.observe(viewLifecycleOwner) {
        val statesFavoriteInteractor = StatesFavoriteInteractor(binding, it, viewModel, layoutInflater)
        statesFavoriteInteractor.init()
    }

    }

}
