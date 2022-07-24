package com.amk.mylibrary.utils

const val TYPE_OF_SORT = "Type_sort"
const val DIRECTION_OF_SORT = "Direction_sort"
const val TYPE_SORT = "sort"
const val TYPE_DIRECTION = "direction"
const val TYPE_FAVORITE = "favorite"
const val KEY = "sort_key"
const val KEY_PREF_THEME = "key_pref_theme"
const val ARGUMENT_KEY = "args_key"
val DEFAULT_DIRECTION_SORT = Direction.Down
val DEFAULT_TYPE_SORT = TypeSort.Percent
val DEFAULT_FIRST = FavoriteState.FavoriteMix

sealed interface Direction : java.io.Serializable {
    object Up : Direction
    object Down : Direction
}

sealed interface TypeSort : java.io.Serializable {
    object Name : TypeSort
    object Price : TypeSort
    object ChangePrice : TypeSort
    object Percent : TypeSort
}

sealed interface FavoriteState : java.io.Serializable {
    object FavoriteMix : FavoriteState
    object FavoriteUp : FavoriteState
}
