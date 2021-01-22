package com.example.gb_material

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.gb_material.fragment.pod.PictureOfTheDayFragment
import com.example.gb_material.fragment.view_pager.adapter.podViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

const val sharedPreferencesName: String = "app"
const val themeIdFieldName: String = "themeId"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var prefs = applicationContext.getSharedPreferences(sharedPreferencesName, MODE_PRIVATE)
        var themeId = prefs.getInt(themeIdFieldName, R.style.BlackAndWhiteTheme)
        setTheme(themeId)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(my_toolbar)
        pod_view_pager.adapter = podViewPagerAdapter(supportFragmentManager)
        pod_tab_layout.setupWithViewPager(pod_view_pager)
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

    private fun changeTheme(themeId: Int) {
        var prefs = applicationContext.getSharedPreferences(sharedPreferencesName, MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt(themeIdFieldName, themeId)
        editor.apply()
        recreate()
    }
}