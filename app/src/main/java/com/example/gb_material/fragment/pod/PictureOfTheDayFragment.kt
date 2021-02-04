package com.example.gb_material.fragment.pod

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Spannable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionManager
import coil.api.load
import com.example.gb_material.R
import com.example.gb_material.fragment.view_pager.PODViewPagerFragment
import com.example.gb_material.web.pod.PictureOfTheDayData
import com.example.gb_material.web.pod.sdf
import kotlinx.android.synthetic.main.pod_fragment.*
import java.util.*

const val dateFieldName = "date"

class PictureOfTheDayFragment(var date: String?, var viewPagerFragment: PODViewPagerFragment?): Fragment(), DatePickerDialog.OnDateSetListener {
    constructor() : this(null, null)
    private var datePickerDialog : DialogFragment? = null
    private var description_header: String? = null
    private var description: Spannable? = null

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProviders.of(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.pod_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (date != null) {
            group_date.visibility = View.GONE
            viewModel.getData(date).observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
            savedInstanceState?.putString(dateFieldName, date)
        } else {
            tv_pod_date.text = sdf.format(Date())
            btn_show_date_picker_dlg.setOnClickListener {
                if (datePickerDialog == null)
                    datePickerDialog = DatePickerFragment(this)
                datePickerDialog?.show(childFragmentManager, "datePicker")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        web_view.settings.javaScriptEnabled = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(dateFieldName, date)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (date == null)
            date = savedInstanceState?.getString(dateFieldName)
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "URL картинки пустой", Toast.LENGTH_SHORT).show()
                } else {
                    if (serverResponseData.mediaType == "video") {
                        eiv_pod.visibility = View.GONE
                        web_view.visibility = View.VISIBLE
                        web_view.loadUrl(url)
                    } else {
                        web_view.visibility = View.GONE
                        eiv_pod.visibility = View.VISIBLE
                        eiv_pod.load(url) {
                            lifecycle(this@PictureOfTheDayFragment)
                            error(R.drawable.ic_baseline_image_not_supported_24)
                            placeholder(R.drawable.ic_baseline_sync_24)
                            target {
                                eiv_pod.setImageDrawable(it)
                                TransitionManager.beginDelayedTransition(eiv_layout)
                                eiv_layout.visibility = View.VISIBLE
                            }
                        }
                    }
                }
                description_header = serverResponseData.title
                description = makeSpanLinks(serverResponseData.explanation, context)
                viewPagerFragment?.updateBottomSheet(description_header, description)
            }
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        TransitionManager.beginDelayedTransition(eiv_layout)
        eiv_layout.visibility = View.GONE

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val date = sdf.format(calendar.time)
        tv_pod_date.text = date
        viewModel.getData(date).observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
    }

    class DatePickerFragment(val listener: DatePickerDialog.OnDateSetListener) : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            // Use the current date as the default date in the picker
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // Create a new instance of DatePickerDialog and return it
            return DatePickerDialog(requireContext(), listener, year, month, day)
        }
    }

    override fun onResume() {
        viewPagerFragment?.updateBottomSheet(description_header, description)
        super.onResume()
    }
}