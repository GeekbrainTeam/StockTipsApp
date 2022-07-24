package ru.amk.favorite.interactors

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.FavoriteCompany
import ru.amk.favorite.databinding.FragmentFavoriteBinding
import ru.amk.favorite.presentation.FavoriteViewModel
import ru.amk.favorite.presentation.adapter.FavoriteCompaniesAdapter

class RecyclerViewFavoriteState(
    private val binding: FragmentFavoriteBinding,
    private val viewModel: FavoriteViewModel,
) : FavoriteCompaniesAdapter.FavoriteClickDeleteInterface {

    private val recyclerView: RecyclerView = binding.recyclerFavoriteCompanies
    private val adapter = FavoriteCompaniesAdapter(this)
    private var position: Int = 0

    init {
        recyclerView.layoutManager = LinearLayoutManager(
            binding.root.context,
            LinearLayoutManager.VERTICAL, false
        )
        recyclerView.adapter = FavoriteCompaniesAdapter(this)
        position = recyclerView.computeVerticalScrollOffset()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        position = recyclerView.computeVerticalScrollOffset()
                    }
                }
            }
        })
    }

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

    internal fun setRecyclerView(listFavorite: List<FavoriteCompany>) {
        adapter.submitList(listFavorite)
        recyclerView.adapter = adapter
        recyclerView.scrollBy(0, position)
    }

    override fun onDeleteIconClick(favorite: FavoriteCompany) {
        viewModel.deleteFavoriteCompany(favorite.secId)
    }
}