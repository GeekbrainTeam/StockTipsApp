package com.amk.mylibrary.utils

const val TYPE_OF_SORT = "Type_sort"
const val DIRECTION_OF_SORT = "Direction_sort"
const val FILTER_ORDER = "order"
const val NO_NULL_ACTIVITY = "Activity cannot be null"
const val KEY = "sort_key"
const val KEY_FILTER = "filter_key"
const val KEY_THEME = "key_theme"
const val KEY_PREF_THEME = "key_pref_theme"
const val ARGUMENT_KEY = "args_key"
val DEFAULT_DIRECTION_SORT = Direction.Up
val DEFAULT_TYPE_SORT = TypeSort.Name
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
