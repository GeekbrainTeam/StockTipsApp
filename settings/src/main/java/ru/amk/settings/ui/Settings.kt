package ru.amk.settings.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amk.mylibrary.utils.KEY_PREF_THEME
import com.amk.mylibrary.utils.TYPE_DIRECTION
import com.amk.mylibrary.utils.TYPE_FAVORITE
import com.amk.mylibrary.utils.TYPE_SORT
import ru.amk.settings.R
import ru.amk.settings.databinding.FragmentSettingsBinding

class Settings : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRadioButton()
        binding.switchThemeGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.light_theme_button -> sendData(1)
                R.id.dark_theme_button -> sendData(2)
                R.id.system_theme_button -> sendData(3)
            }
        }
        binding.switchTypeSortDefault.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.name_default -> sendTypeDefault(TYPE_SORT, 1)
                R.id.price_default -> sendTypeDefault(TYPE_SORT, 2)
                R.id.change_price_default -> sendTypeDefault(TYPE_SORT, 3)
                R.id.change_percent_default -> sendTypeDefault(TYPE_SORT, 4)
            }
        }
        binding.switchTypeDirectionDefault.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.direction_up_default -> sendTypeDefault(TYPE_DIRECTION, 1)
                R.id.direction_down_default -> sendTypeDefault(TYPE_DIRECTION, 2)
            }
        }
        binding.switchFavoriteDefault.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.favorite_default -> sendTypeDefault(TYPE_FAVORITE, 1)
                R.id.mix_default -> sendTypeDefault(TYPE_FAVORITE, 2)
            }
        }
    }

    private fun sendData(key: Int) {
        val sharedPrefsThemes =
            requireContext().getSharedPreferences(KEY_PREF_THEME, Context.MODE_PRIVATE)
        sharedPrefsThemes.edit().putInt(KEY_PREF_THEME, key).apply()
        val intent = Intent(requireContext(), requireActivity()::class.java)
        requireActivity().startActivity(intent)
    }

    private fun sendTypeDefault(type: String, key: Int) {
        val sharedPrefs =
            requireContext().getSharedPreferences(type, Context.MODE_PRIVATE)
        sharedPrefs.edit().putInt(type, key).apply()
    }

    private fun setRadioButton() {
        val sharedPrefsSort = requireContext().getSharedPreferences(TYPE_SORT, Context.MODE_PRIVATE)
        val sharedPrefsDirection =
            requireContext().getSharedPreferences(TYPE_DIRECTION, Context.MODE_PRIVATE)
        val sharedPrefsFavorite =
            requireContext().getSharedPreferences(TYPE_FAVORITE, Context.MODE_PRIVATE)
        val sort = sharedPrefsSort.getInt(TYPE_SORT, 0)
        val direction = sharedPrefsDirection.getInt(TYPE_DIRECTION, 0)
        val favorite = sharedPrefsFavorite.getInt(TYPE_FAVORITE, 0)
        when (sort) {
            1 -> binding.nameDefault.isChecked = true
            2 -> binding.priceDefault.isChecked = true
            3 -> binding.changePriceDefault.isChecked = true
            4 -> binding.changePercentDefault.isChecked = true
        }
        when (direction) {
            1 -> binding.directionUpDefault.isChecked = true
            2 -> binding.directionDownDefault.isChecked = true
        }
        when (favorite) {
            1 -> binding.favoriteDefault.isChecked = true
            2 -> binding.mixDefault.isChecked = true
        }
    }
}