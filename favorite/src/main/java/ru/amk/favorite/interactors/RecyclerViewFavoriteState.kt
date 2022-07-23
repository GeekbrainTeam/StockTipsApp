package ru.amk.favorite.interactors

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.FavoriteCompanyShow
import ru.amk.favorite.databinding.FragmentFavoriteBinding
import ru.amk.favorite.presentation.FavoriteViewModel
import ru.amk.favorite.presentation.adapter.FavoriteCompaniesAdapter

class RecyclerViewFavoriteState(
    private val binding: FragmentFavoriteBinding,
    private val viewModel: FavoriteViewModel,
) : FavoriteCompaniesAdapter.FavoriteClickDeleteInterface {
    internal fun loading() {
        binding.recyclerFavoriteCompanies.isVisible = false
        binding.progressBarFavorite.isVisible = true
    }

    internal fun error() {
        binding.recyclerFavoriteCompanies.isVisible = false
        binding.progressBarFavorite.isVisible = false
    }

    internal fun success() {
        binding.recyclerFavoriteCompanies.isVisible = true
        binding.progressBarFavorite.isVisible = false
    }

    internal fun setRecyclerView(listFavorite: List<FavoriteCompanyShow>) {
        val adapter = FavoriteCompaniesAdapter(this)
        val recyclerView: RecyclerView = binding.recyclerFavoriteCompanies
        recyclerView.layoutManager = LinearLayoutManager(
            binding.root.context,
            LinearLayoutManager.VERTICAL, false
        )

        recyclerView.adapter = FavoriteCompaniesAdapter(
            //submitList = listFavorite,
            favoriteClickDeleteInterface = this
        )
        adapter.submitList(listFavorite)
        recyclerView.adapter = adapter
    }

    override fun onDeleteIconClick(favorite: FavoriteCompanyShow) {
        viewModel.deleteFavoriteCompany(favorite.secId)
    }
}