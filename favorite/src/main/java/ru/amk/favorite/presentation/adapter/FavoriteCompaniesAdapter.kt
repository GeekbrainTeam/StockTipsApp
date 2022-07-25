package ru.amk.favorite.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.FavoriteCompany
import ru.amk.favorite.databinding.ItemFavoriteBinding


class FavoriteCompaniesAdapter(
    private val favoriteClickDeleteInterface: FavoriteClickDeleteInterface,
    private val context: Context
) :
    RecyclerView.Adapter<FavoriteCompaniesHolder>() {
    private val diffUtil = AsyncListDiffer(this, DIFF_CALLBACK)

    interface FavoriteClickDeleteInterface {
        fun onDeleteIconClick(favorite: FavoriteCompany)
    }
    fun submitList(newList: List<FavoriteCompany>) {
        diffUtil.submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCompaniesHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoriteCompaniesHolder(ItemFavoriteBinding.inflate(inflater, parent, false), context)
    }

    override fun onBindViewHolder(holder: FavoriteCompaniesHolder, position: Int) {
        val favorite: FavoriteCompany = diffUtil.currentList[position]
        holder.bind(favorite)
        holder.onDeleteClick.setOnClickListener {
            favoriteClickDeleteInterface.onDeleteIconClick(favorite)
        }
    }

    override fun getItemCount(): Int = diffUtil.currentList.size

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<FavoriteCompany> =
            object : DiffUtil.ItemCallback<FavoriteCompany>() {

                override fun areItemsTheSame(
                    oldItem: FavoriteCompany,
                    newItem: FavoriteCompany
                ): Boolean =
                    oldItem.secId == newItem.secId && oldItem.favorite == newItem.favorite

                override fun areContentsTheSame(
                    oldItem: FavoriteCompany,
                    newItem: FavoriteCompany
                ): Boolean =
                    oldItem == newItem
            }
    }
}

