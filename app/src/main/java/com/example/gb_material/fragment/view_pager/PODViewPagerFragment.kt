package com.example.gb_material.fragment.view_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gb_material.R
import com.example.gb_material.fragment.view_pager.adapter.PODViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.page_container.*

class PODViewPagerFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.page_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pod_tab_layout.tabMode = TabLayout.MODE_FIXED
        pod_view_pager.adapter = PODViewPagerAdapter(childFragmentManager)
        pod_tab_layout.setupWithViewPager(pod_view_pager)
    }
}