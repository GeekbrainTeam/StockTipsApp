package ru.amk.favorite.interactors

import android.widget.Toast
import ru.amk.favorite.databinding.FragmentFavoriteBinding
import ru.amk.favorite.presentation.FavoriteViewModel

class StatesFavoriteInteractor(
    private val binding: FragmentFavoriteBinding,
    private val state: FaforiteFragmentState,
    viewModel: FavoriteViewModel,
) {
    private val recyclerViewFavoriteState = RecyclerViewFavoriteState(binding, viewModel)
    fun init() {
        when (state) {
            is FaforiteFragmentState.Loading -> {
                recyclerViewFavoriteState.loading()
            }
            is FaforiteFragmentState.Failure -> {
                recyclerViewFavoriteState.error()
                Toast.makeText(binding.root.context, state.toString(), Toast.LENGTH_LONG).show()
            }
            is FaforiteFragmentState.Success -> {
                recyclerViewFavoriteState.success()
                recyclerViewFavoriteState.setRecyclerView(state.data)
            }
            else -> {
                FaforiteFragmentState.Empty
            }
        }
    }
}