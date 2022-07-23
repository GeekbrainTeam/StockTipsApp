package ru.amk.favorite.ui

import android.os.Bundle
import android.view.View
import com.amk.core.ui.BaseFragment
import ru.amk.favorite.databinding.FragmentFavoriteBinding
import ru.amk.favorite.interactors.StatesFavoriteInteractor
import ru.amk.favorite.presentation.FavoriteViewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    override fun getViewBinding() = FragmentFavoriteBinding.inflate(layoutInflater)
    override fun getVModelClass() = FavoriteViewModel::class.java

    private val statesFavoriteInteractor: StatesFavoriteInteractor by lazy {
        StatesFavoriteInteractor(binding, viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.companiesData.observe(viewLifecycleOwner) {
            statesFavoriteInteractor.init(it)
        }
    }

}
