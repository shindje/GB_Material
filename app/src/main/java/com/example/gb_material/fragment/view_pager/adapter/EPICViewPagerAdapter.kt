package com.example.gb_material.fragment.view_pager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.gb_material.fragment.epic.EarthCameraFragment
import com.example.gb_material.web.epic.EPICServerResponseData

class EPICViewPagerAdapter (private val fragmentManager: FragmentManager, private val epicDataList: List<EPICServerResponseData>) :
        FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return EarthCameraFragment(epicDataList[position].date, epicDataList[position].image)
    }

    override fun getCount(): Int {
        return epicDataList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return position.toString()
    }
}
