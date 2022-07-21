package ru.amk.settings.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amk.core.navigation.AppNavigation
import com.amk.mylibrary.utils.KEY_PREF_THEME
import com.amk.mylibrary.utils.KEY_THEME
import org.koin.android.ext.android.inject
import ru.amk.settings.R
import ru.amk.settings.databinding.FragmentSettingsBinding

class Settings : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val coordinator: AppNavigation by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initTheme()
        binding.switchThemeGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.light_theme_button -> sendData(1)
                R.id.dark_theme_button -> sendData(2)
                R.id.system_theme_button -> sendData(3)
            }
        }
    }

    private fun sendData(key: Int) {
        val sharedPrefs =
            requireContext().getSharedPreferences(KEY_PREF_THEME, Context.MODE_PRIVATE)
        sharedPrefs.edit().putInt(KEY_PREF_THEME, key).apply()
        val intent = Intent(requireActivity().baseContext, requireActivity()::class.java)
        requireActivity().startActivity(intent)
    }

    private fun initTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            binding.systemThemeButton.visibility = View.VISIBLE
        } else {
            binding.systemThemeButton.visibility = View.GONE
        }
        when (getSavedTheme()) {
            1 -> binding.lightThemeButton.isChecked = true
            2 -> binding.darkThemeButton.isChecked = true
            3 -> binding.systemThemeButton.isChecked = true
            0 -> {
                when (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
                    Configuration.UI_MODE_NIGHT_NO -> binding.lightThemeButton.isChecked = true
                    Configuration.UI_MODE_NIGHT_YES -> binding.darkThemeButton.isChecked = true
                    Configuration.UI_MODE_NIGHT_UNDEFINED -> binding.lightThemeButton.isChecked =
                        true
                }
            }
        }
    }

    private fun getSavedTheme(): Int {
        val sharedPrefs =
            requireContext().getSharedPreferences(KEY_PREF_THEME, Context.MODE_PRIVATE)
        return sharedPrefs.getInt(KEY_THEME, 0)
    }
}