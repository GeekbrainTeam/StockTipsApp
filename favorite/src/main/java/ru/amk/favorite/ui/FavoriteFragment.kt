package ru.amk.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amk.core.ui.BaseFragment
import ru.amk.favorite.databinding.FragmentFavoriteBinding
import ru.amk.favorite.interactors.StatesFavoriteInteractor
import ru.amk.favorite.presentation.FavoriteViewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    override fun getViewBinding() = FragmentFavoriteBinding.inflate(layoutInflater)
    override fun getVModelClass() = FavoriteViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.companiesData.observe(viewLifecycleOwner) {
            val statesFavoriteInteractor = StatesFavoriteInteractor(binding, it, viewModel)
            statesFavoriteInteractor.init()
        }
        //viewModel.getFavoriteCompanyIsShow()
    }
}
