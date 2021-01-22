package com.example.gb_material.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gb_material.MainActivity
import com.example.gb_material.R
import kotlinx.android.synthetic.main.settings.*

class SettingsFragment: Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_btw_theme.setOnClickListener { (requireActivity() as MainActivity).changeTheme(R.style.BlackAndWhiteTheme) }
        btn_color_theme.setOnClickListener { (requireActivity() as MainActivity).changeTheme(R.style.ColourfulTheme) }
    }

}