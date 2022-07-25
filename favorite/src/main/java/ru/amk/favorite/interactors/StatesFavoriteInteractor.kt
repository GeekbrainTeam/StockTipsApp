package ru.amk.favorite.interactors

import android.content.Context
import android.widget.Toast
import com.amk.core.entity.FaforiteFragmentState
import ru.amk.favorite.databinding.FragmentFavoriteBinding
import ru.amk.favorite.presentation.FavoriteViewModel

class StatesFavoriteInteractor(
    private val binding: FragmentFavoriteBinding,
    viewModel: FavoriteViewModel,
    context: Context
) {
    private val recyclerViewFavoriteState = RecyclerViewFavoriteState(binding, viewModel, context)
    fun init(state: FaforiteFragmentState) {
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