package com.example.gb_material.fragment.view_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.gb_material.R
import com.example.gb_material.fragment.epic.EarthCameraViewModel
import com.example.gb_material.fragment.view_pager.adapter.EPICViewPagerAdapter
import com.example.gb_material.web.epic.EarthCameraData
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import kotlinx.android.synthetic.main.page_container.*

class EPICViewPagerFragment : Fragment() {
    private val viewModel: EarthCameraViewModel by lazy {
        ViewModelProviders.of(this).get(EarthCameraViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.page_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pod_tab_layout.tabMode = MODE_SCROLLABLE
        viewModel.getData().observe(viewLifecycleOwner, Observer<EarthCameraData> {
            when (it) {
                is EarthCameraData.Success -> {
                    pod_view_pager.adapter = EPICViewPagerAdapter(childFragmentManager, it.serverResponseData)
                    pod_tab_layout.setupWithViewPager(pod_view_pager)
                }
            }
        })
    }
}