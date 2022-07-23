package ru.amk.favorite.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.Company
import com.amk.core.entity.FavoriteCompanyShow
import ru.amk.favorite.databinding.ItemFavoriteBinding


class FavoriteCompaniesAdapter(
    //private val list: List<FavoriteCompanyShow>,
    //private val onClickListener: OnStateClickListener,
    private val favoriteClickDeleteInterface: FavoriteClickDeleteInterface
) :
    RecyclerView.Adapter<FavoriteCompaniesHolder>() {
    private val diffUtil = AsyncListDiffer(this, DIFF_CALLBACK)

    /*interface OnStateClickListener {
        fun onStateClick(secId: String, position: Int)
    }*/

    interface FavoriteClickDeleteInterface {
        fun onDeleteIconClick(favorite: FavoriteCompanyShow)
    }
    fun submitList(newList: List<FavoriteCompanyShow>) {
        diffUtil.submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCompaniesHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoriteCompaniesHolder(ItemFavoriteBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteCompaniesHolder, position: Int) {
        val favorite: FavoriteCompanyShow = diffUtil.currentList[position]
        holder.bind(favorite)
        /*holder.itemView.setOnClickListener {
            onClickListener.onStateClick(secId, position)
        }*/
        holder.onDeleteClick.setOnClickListener {
            favoriteClickDeleteInterface.onDeleteIconClick(favorite)
        }
    }

    override fun getItemCount(): Int = diffUtil.currentList.size

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<FavoriteCompanyShow> =
            object : DiffUtil.ItemCallback<FavoriteCompanyShow>() {

                override fun areItemsTheSame(
                    oldItem: FavoriteCompanyShow,
                    newItem: FavoriteCompanyShow
                ): Boolean =
                    oldItem.secId == newItem.secId

                override fun areContentsTheSame(
                    oldItem: FavoriteCompanyShow,
                    newItem: FavoriteCompanyShow
                ): Boolean =
                    oldItem == newItem
            }
    }
}

