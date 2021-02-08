package com.example.gb_material

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.gb_material.fragment.SettingsFragment
import com.example.gb_material.fragment.notes.NotesFragment
import com.example.gb_material.fragment.view_pager.EPICViewPagerFragment
import com.example.gb_material.fragment.view_pager.PODViewPagerFragment
import kotlinx.android.synthetic.main.activity_main.*

const val sharedPreferencesName: String = "app"
const val themeFieldName: String = "theme"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var prefs = applicationContext.getSharedPreferences(sharedPreferencesName, MODE_PRIVATE)
        var themeId = getThemeIdByName(prefs.getString(themeFieldName, "BlackAndWhiteTheme"))
        setTheme(themeId)
        setContentView(R.layout.activity_main)
        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_notes -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragments_container, NotesFragment())
                            .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_pod -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragments_container, PODViewPagerFragment())
                            .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_epic -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragments_container, EPICViewPagerFragment())
                            .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_settings -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragments_container, SettingsFragment())
                            .commitAllowingStateLoss()
                    true
                }
                else -> false
            }
        }
        bottom_navigation_view.selectedItemId = R.id.bottom_view_pod
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.top_action_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.menu_baw_theme -> changeTheme(R.style.BlackAndWhiteTheme)
            R.id.menu_colourful_theme -> changeTheme(R.style.ColourfulTheme)

        }
        return true
    }

    fun changeTheme(themeId: Int) {
        var prefs = applicationContext.getSharedPreferences(sharedPreferencesName, MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(themeFieldName, getThemeNameById(themeId))
        editor.apply()
        recreate()
    }

    fun changeArrowImageVisibility(visibility: Int) {
        iv_arrow_down.visibility = visibility
    }

    fun changeArrowImageSelected(selected: Boolean) {
        iv_arrow_down.isSelected = selected
    }

    private fun getThemeNameById(themeId: Int) =
        when(themeId) {
            R.style.BlackAndWhiteTheme -> "BlackAndWhiteTheme"
            R.style.ColourfulTheme -> "ColourfulTheme"
            else -> null
        }

    private fun getThemeIdByName(themeName: String?) =
            when(themeName) {
                "BlackAndWhiteTheme" -> R.style.BlackAndWhiteTheme
                "ColourfulTheme" -> R.style.ColourfulTheme
                else -> R.style.BlackAndWhiteTheme
            }
}