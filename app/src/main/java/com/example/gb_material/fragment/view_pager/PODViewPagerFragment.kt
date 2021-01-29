package com.example.gb_material.fragment.view_pager

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.gb_material.R
import com.example.gb_material.fragment.view_pager.adapter.PODViewPagerAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.page_container.*
import kotlinx.android.synthetic.main.page_container.nest_scrollview
import kotlinx.android.synthetic.main.page_container.pod_tab_layout
import kotlinx.android.synthetic.main.page_container.pod_view_pager
import kotlinx.android.synthetic.main.page_container.wiki_motion_layout

class PODViewPagerFragment : Fragment() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.page_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pod_tab_layout.tabMode = TabLayout.MODE_FIXED
        pod_view_pager.adapter = PODViewPagerAdapter(childFragmentManager, this)
        pod_tab_layout.setupWithViewPager(pod_view_pager)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        nest_scrollview.isFillViewport = true
        input_layout_wiki.setStartIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${et_wiki.text.toString()}")
            })
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    fun updateBottomSheet(header: String?, description: String?) {
        bottom_sheet_description_header?.text = header
        bottom_sheet_description?.text = description
    }

    fun changeWikiVisibility(visibilty: Int) {
        wiki_motion_layout.visibility = visibilty
    }
}