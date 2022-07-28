package ru.amk.favorite.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.amk.core.ui.BaseFragment
import ru.amk.favorite.databinding.FragmentFavoriteBinding
import ru.amk.favorite.interactors.StatesFavoriteInteractor
import ru.amk.favorite.presentation.FavoriteViewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    override fun getViewBinding() = FragmentFavoriteBinding.inflate(layoutInflater)
    override fun getVModelClass() = FavoriteViewModel::class.java

    private val statesFavoriteInteractor: StatesFavoriteInteractor by lazy {
        StatesFavoriteInteractor(binding, viewModel, requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.companiesData.observe(viewLifecycleOwner) {
            statesFavoriteInteractor.init(it)
        }
        val getDataFromActivity = requireActivity() as SearchFromFavorite
        getDataFromActivity.getQueryFavoriteCompany().observe(viewLifecycleOwner) {
            viewModel.filteredFavoriteCompany(it)
        }
       /* binding.searchInputFavoriteText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.filteredFavoriteCompany(s)
            }
        })*/
    }

}
