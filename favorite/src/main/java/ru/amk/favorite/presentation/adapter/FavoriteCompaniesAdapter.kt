package ru.amk.favorite.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.FavoriteCompany
import ru.amk.favorite.databinding.ItemFavoriteBinding


class FavoriteCompaniesAdapter(
    private val list: List<FavoriteCompany>,
    //private val onClickListener: OnStateClickListener,
    private val favoriteClickDeleteInterface: FavoriteClickDeleteInterface
) :
    RecyclerView.Adapter<FavoriteCompaniesHolder>() {

    /*interface OnStateClickListener {
        fun onStateClick(secId: String, position: Int)
    }*/

    interface FavoriteClickDeleteInterface {
        fun onDeleteIconClick(favorite: FavoriteCompany)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCompaniesHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoriteCompaniesHolder(ItemFavoriteBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteCompaniesHolder, position: Int) {
        val favorite: FavoriteCompany = list[position]
        holder.bind(favorite)
        /*holder.itemView.setOnClickListener {
            onClickListener.onStateClick(secId, position)
        }*/
        holder.onDeleteClick.setOnClickListener {
            favoriteClickDeleteInterface.onDeleteIconClick(favorite)
        }
    }

    override fun getItemCount(): Int = list.size

}

