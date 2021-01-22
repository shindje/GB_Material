package com.example.gb_material.fragment.view_pager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gb_material.fragment.pod.PictureOfTheDayFragment
import com.example.gb_material.web.pod.sdf
import java.util.*

private const val YESTERDAY = 0
private const val TODAY = 1
private const val DATE = 2

class podViewPagerAdapter(private val fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = arrayOf(
            PictureOfTheDayFragment(sdf.format(Date(System.currentTimeMillis() - 24*60*60*1000))),
            PictureOfTheDayFragment(sdf.format(Date())),
            PictureOfTheDayFragment(null)
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> fragments[YESTERDAY]
            2 -> fragments[DATE]
            else -> fragments[TODAY]
        }
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Вчера"
            2 -> "Дата"
            else -> "Сегодня"
        }
    }
}
