package ru.amk.favorite.presentation.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.FavoriteCompanyShow
import ru.amk.favorite.databinding.ItemFavoriteBinding


class FavoriteCompaniesAdapter(
    private val list: List<FavoriteCompanyShow>,
    private val layoutInflater: LayoutInflater,
    private val favoriteClickDeleteInterface: FavoriteClickDeleteInterface
) :
    RecyclerView.Adapter<FavoriteCompaniesHolder>() {

    interface FavoriteClickDeleteInterface {
        fun onDeleteIconClick(favorite: FavoriteCompanyShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCompaniesHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoriteCompaniesHolder(ItemFavoriteBinding.inflate(inflater, parent, false), layoutInflater)
    }

    override fun onBindViewHolder(holder: FavoriteCompaniesHolder, position: Int) {
        val favorite: FavoriteCompanyShow = list[position]
        holder.bind(favorite)
        holder.onDeleteClick.setOnClickListener {
            favoriteClickDeleteInterface.onDeleteIconClick(favorite)
        }
    }

    override fun getItemCount(): Int = list.size

}

