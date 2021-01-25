package com.example.gb_material.fragment.epic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.gb_material.BuildConfig
import com.example.gb_material.R
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.epic_fragment.*
import kotlinx.android.synthetic.main.pod_fragment.*
import java.util.*

const val dateFieldName = "date"
const val imageFieldName = "image"

class EarthCameraFragment (var date: String?, var image: String?): Fragment() {
    constructor() : this(null, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (date == null)
            date = savedInstanceState?.getString(dateFieldName)
        if (image == null)
            image = savedInstanceState?.getString(imageFieldName)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.epic_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_epic_date.text = date
        val dateString = date?.subSequence(0, date!!.indexOf(" ")).toString().replace("-", "/")
        val apiKey: String = BuildConfig.NASA_API_KEY
        val url = "https://api.nasa.gov/EPIC/archive/natural/$dateString/png/$image.png?api_key=$apiKey"
        eiv_epic.load(url) {
            lifecycle(this@EarthCameraFragment)
            error(R.drawable.ic_baseline_image_not_supported_24)
            placeholder(R.drawable.ic_baseline_sync_24)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(dateFieldName, date)
        outState.putString(imageFieldName, image)
    }
}